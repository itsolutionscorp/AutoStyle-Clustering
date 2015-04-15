from datetime import timedelta

def add_gigasecond(birth_date):
    """ Add one billion seconds to the provided date. """
    try:
        return birth_date + timedelta(seconds=10**9)
    except TypeError:
        print("Please provide a date.")
