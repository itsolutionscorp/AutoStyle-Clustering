def is_leap_year(year):
  # Condense if-else into single return
  return (year%4==0 and year%100!=0) or year%400==0
