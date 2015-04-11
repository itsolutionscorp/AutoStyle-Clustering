from datetime import date, timedelta
#It's pretty unclear what level of detail they want us to implement here so i just took the easy way out.
def add_gigasecond(input_date):
    return input_date + timedelta(seconds = 10 **9)
