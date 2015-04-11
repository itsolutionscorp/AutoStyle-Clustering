'''exer sieve'''


def sieve(num):
    '''implement sieve of eratosthones for numbers from to 2 - num'''
    eras = range(2, num+1)      # create the list [2, 3, 4, 5] for sieve(5)
    location = 0                # starting at the first location
    while location < num-1:     # while not out-of-bounds
        location_value = eras[location]  # determine the value at location
        if location_value:               # we use 0 to blank a location
            next_location = location + location_value  # inc by multiple
            while next_location < num-1:    # while not out-of-bounds
                eras[next_location] = 0     # blank the location [2, 3, 0, 5]
                next_location += location_value  # inc the next_location

        location += 1                   # scan the next location

    return [x for x in eras if x]   # filter out 0 values and return result
