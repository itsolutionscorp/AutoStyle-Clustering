"""exercism.io - Python 'Gigasecond' exercise solution."""
from datetime import date

def add_gigasecond(init_date):
    # 1 Gigasecond == 11 574 days
    ordinal = init_date.toordinal() + 11574
    return date.fromordinal(ordinal)
