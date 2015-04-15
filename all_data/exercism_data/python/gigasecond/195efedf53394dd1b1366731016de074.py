from datetime import timedelta
def add_gigasecond(input_date) :
  return ( input_date + timedelta(0, 10**9) )
