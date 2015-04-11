#!/usr/bin/python
from datetime import date
def add_gigasecond(d):
    Gs = 10**9 / (24 * 60 * 60.0)
    months = [31, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    get_months = (lambda x: months[:1] + [29] + months[1:] 
            if x % 400 == 0 or (x % 100 != 0 and x % 4 == 0)
            else months[:1] + [28] + months[1:])
    days_left_in_current_year = (sum(get_months(d.year)) - 
                                (sum(get_months(d.year)[:(d.month-1)]) + d.day))
    rough_years_to_go = int((Gs - days_left_in_current_year) / 365)
    days_left_in_final_year = (Gs - days_left_in_current_year - 
                                sum(map(lambda x: 366 if x % 400 == 0 or 
                                                        (x % 100 != 0 and x % 4 == 0) 
                                                      else 365, 
                                    range(d.year+1, d.year+rough_years_to_go+1))))
    new_year = d.year+rough_years_to_go+1
    new_month, new_day = [(x[1], x[2]) for x in filter(lambda x: x[0] < days_left_in_final_year,
                                                        [(sum(get_months(new_year)[:x]),
                                                            x + 1,
                                                            int(days_left_in_final_year) - sum(get_months(new_year)[:x]))
                                                        for x in range(len(get_months(new_year)))])
                                                        ][-1]
    return date(new_year, new_month, new_day)
