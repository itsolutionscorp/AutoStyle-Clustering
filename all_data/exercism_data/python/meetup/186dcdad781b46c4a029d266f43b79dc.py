from datetime import date
from calendar import monthrange

def meetup_day(year, month, day, position):
    """Returns date of meetup"""

    # Create a numerical mapping for weekday
    mapWeekday = { 
                   'Monday': 0,
                   'Tuesday': 1,
                   'Wednesday': 2,
                   'Thursday': 3,
                   'Friday': 4,
                   'Saturday': 5,
                   'Sunday': 6
        }

    # Create a numerical mapping for weekday position within month
    mapPosition = {
                    '1st': 0,
                    '2nd': 1,
                    '3rd': 2,
                    '4th': 3,
                    '5th': 4,
                    'last': -1,
                    'teenth': 1
        }

    # map day argument to number
    day = mapWeekday[day]

    # Map weekday position to number
    posIndex = mapPosition[position]

    # Get max days in month provided
    mRange = monthrange(year, month)

    # Calculate possible dates of weekday provided in that month and year
    possibleDates = [ p for p in range(1, mRange[1]+1)  if date(year, month, p).weekday() == day ]

    # Adjust teenth (for flaw in calculation methodology)
    if position == 'teenth' and possibleDates[posIndex] < 13:
        posIndex += 1

    # Return date
    return date(year, month, possibleDates[posIndex])
