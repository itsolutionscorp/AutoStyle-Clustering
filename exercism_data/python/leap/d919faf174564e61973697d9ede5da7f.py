import sys


def is_leap_year(year):
    try:
        year = int(year)
    except ValueError, e:
        print >> sys.stderr, '%s is not an integer' % year
        print >> sys.stderr, e
        sys.exit(1)

    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True
    return False


if __name__ == '__main__':
    print is_leap_year(sys.argv[1])
