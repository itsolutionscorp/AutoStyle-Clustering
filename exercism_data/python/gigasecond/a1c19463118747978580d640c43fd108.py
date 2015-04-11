from datetime import timedelta


def add_gigasecond(date_of_birth):

    # Add one billion seconds to the date passed and return
    return date_of_birth + timedelta(0,1000000000)
