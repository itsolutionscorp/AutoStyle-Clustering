from datetime import timedelta


def add_giga_second(datestamp):
    gs = 10 ** 9
    return datestamp + timedelta(0, gs)
