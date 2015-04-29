from datetime import date

def add_gigasecond(d):
    o1 = d.toordinal()

    seconds = 10**9
    days = seconds / 86400

    o2 = o1 + days

    return date.fromordinal(o2)

if __name__ == "__main__":
    print add_gigasecond(date(2011, 4, 25))
