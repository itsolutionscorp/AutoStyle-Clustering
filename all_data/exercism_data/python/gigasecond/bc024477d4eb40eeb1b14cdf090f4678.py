from datetime import date


def add_gigasecond(time):
    current_year = time.year
    current_month = time.month
    current_day = time.day
    SEC_PER_DAY = 86400
    days_per_month = {1: 31,
                      2: 28,
                      3: 31,
                      4: 30,
                      5: 31,
                      6: 30,
                      7: 31,
                      8: 31,
                      9: 30,
                      10: 31,
                      11: 30,
                      12: 31
    }
    DaysForGigasec = int(10**9 /SEC_PER_DAY)
    final_days = current_day
    final_month = current_month
    final_year = current_year
    for x in range(DaysForGigasec):
        # update days
        if final_days < days_per_month[final_month]:
            final_days += 1
        elif final_month == 2 and is_leap_year(final_year) and final_days == 28:
            final_days += 1
        else:
            final_month += 1
            final_days = 1
        # update month
        if final_month > 12:
            final_year += 1
            final_month = 1
    return date(final_year, final_month, final_days)

# judge if is leap year
def is_leap_year(year):
    if year % 4 == 0:
        if year % 100 == 0 and year % 400 != 0:
            return False
        else:
            return True
    else:
        return False
