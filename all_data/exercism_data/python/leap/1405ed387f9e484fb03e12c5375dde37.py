import sys

def is_leap_year(input):

    input = int(input)
    is_leap_year = 0

    if( input % 4 == 0 ):
        if ( input % 100 != 0 ):
            is_leap_year = 1
        elif ( input % 100 == 0 and input % 400 ==0):
            is_leap_year = 1

    return is_leap_year


if __name__ == '__main__':
    print is_leap_year(sys.argv[1])
