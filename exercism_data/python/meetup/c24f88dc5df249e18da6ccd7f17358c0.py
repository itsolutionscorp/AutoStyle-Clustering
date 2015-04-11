# Imports
from datetime import date
from calendar import Calendar

# Functions
def meetup_day(year, month, day_name, qualifier):
    # Variables we're going to use:
    day_list   = list(Calendar().itermonthdates(year,month))
    month_dict = {} # Day numbers are keys; day names are definitions.

    for current_day in day_list:
        # Extract certain components from the current day.
        current_day_number   = int(current_day.strftime('%d'))
        current_month_number = int(current_day.strftime('%m'))
        current_day_name     = current_day.strftime('%A')
        
        # If the day is within the current month, add it to the dictionary.
        if current_month_number == month:
            month_dict[current_day_number] = current_day_name
            
    # OK, we (presumably) have a dictionary of day numbers and their names.
    # Now we can figure out what we're looking for.

    # First, let's test for numeric qualifiers.
    if qualifier == '1st' or  qualifier == '2nd' or qualifier == '3rd' or \
        qualifier == '4th' or qualifier == '5th':
            # The first character is also the desired count.
            desired_count = int(qualifier[0])
            
            # We'll need a counter to test against the desired count.
            counter = 0

            for current_day in month_dict.keys():
              if month_dict[current_day] == day_name:
                    counter += 1
                    if counter == desired_count:
                        # We found a date. Return it.
                        return date(year, month, current_day)

    # Next, try testing for the 'teenth' qualifier.
    if qualifier == 'teenth':
        # We're looking for a day number in the teen range.
        for current_day in range(13,20):
            if month_dict[current_day] == day_name:
                # We found a date. Return it.
                return date(year, month, current_day)
    
    # Finally, try testing for the 'last' qualifier.
    if qualifier == 'last':
        # Walk backwards through dictionary looking for the first occurrence.
        for current_day in range (len(month_dict), 1, -1):
            if month_dict[current_day] == day_name:
                # We found a date. Return it.
                return date(year, month, current_day)
