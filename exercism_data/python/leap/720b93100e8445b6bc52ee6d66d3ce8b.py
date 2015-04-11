#Leap year function
def is_leap_year(num):
    if num % 4 == 0:
        if num % 100 == 0 and num % 400 != 0:
            return False;
    else:
        return False

    return True;

#Main
if __name__ == "__main__":

    print("Give me a year number and I'll let you if it's a leap year.");

    while 1:
        ans = int(raw_input("Year: "));
        print(is_leap_year(ans));
