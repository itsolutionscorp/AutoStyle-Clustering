#!/usr/bin/python

def is_leap_year(year):
    if year % 400 == 0:
        return True
    if year % 100 == 0:
        return False
    if year % 4 == 0:
        return True
    return False

def main():
    from argparse import ArgumentParser
    parser = ArgumentParser()
    parser.add_argument("year", metavar="YEAR", type=int)
    args = parser.parse_args()

    print is_leap_year(args.year)

if __name__ == "__main__":
    import sys
    sys.exit(main())
