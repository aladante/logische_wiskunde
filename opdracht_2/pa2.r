library(tidyverse)

# Helper function for printing message along with query result
print_query <- function(query, message) {
  cat("\n", message, "\n")
  query
}

# Define a table for each vehicle type: air_vehicle, land_vehicle &
# water_vehicles
air_vehicles <- tibble(
  id = c(1, 2, 3),
  name = c("Airbus A380-800", "Apollo", "Lockheed Jetstar"),
  flies_in_the_air = c(TRUE, TRUE, TRUE),
  engine = c(TRUE, TRUE, TRUE),
  wings = c(TRUE, FALSE, TRUE),
  wheels = c(20, 0, 6)
)

land_vehicles <- tibble(
  id = c(1, 2, 3),
  name = c("Ferrari", "Gazelle", "Yamaha"),
  drives_on_land = c(TRUE, TRUE, TRUE),
  wheels = c(4, 2, 2),
  engine = c(TRUE, FALSE, TRUE)
)

water_vehicles <- tibble(
  id = c(1, 2, 3),
  name = c("Regal 28 Express", "Lizzie May", "Lazzara 80"),
  moves_via_water = c(TRUE, TRUE, TRUE),
  engine = c(TRUE, FALSE, TRUE)
)

# Full outer join of all vehicle type tables to get a table that lists all
# vehicles. Null/NA values are replaced with FALSE.
vehicles = full_join(air_vehicles, land_vehicles) %>% full_join(water_vehicles) %>% replace(is.na(.),FALSE)
print_query(vehicles, "\nShowing all vehicles in a table\n")

# Land vehicles
bicycles <- vehicles %>% filter(drives_on_land == TRUE, wheels == 2, engine == FALSE)
print_query(bicycles, "\nShowing all bicycles in a table\n")

motor_bikes <- vehicles %>% filter(drives_on_land == TRUE, wheels == 2, engine == TRUE)
print_query(motor_bikes, "\nShowing all motor bikes in a table\n")

cars <- vehicles %>% filter(drives_on_land == TRUE, wheels == 4, engine == FALSE)
print_query(cars, "\nShowing all cars in a table\n")

# Air vehicles
airplanes <- vehicles %>% filter(flies_in_the_air == TRUE, wings == TRUE, engine == TRUE, wheels > 3)
print_query(airplanes, "\nShowing all airplanes in a table\n")

space_crafts <- vehicles %>% filter(flies_in_the_air == TRUE, engine == TRUE, wheels == 0)
print_query(space_crafts, "\nShowing all space crafts in a table\n")

# Water vehicles
boats <- vehicles %>% filter(moves_via_water == TRUE)
print_query(boats, "\nShowing all boats in a table\n")
