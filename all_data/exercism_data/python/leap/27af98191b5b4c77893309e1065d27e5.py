while True:
    try:
        year = input("Enter an year: ")
        if len(year) == 4:
            year = int(year)
        # A gap is left to indicate two different if's.
        if year % 4 == 0 and year % 100 == 0 and year % 400 == 0:
            print("The year is a leap year.")
        else:
            print("The year is not a leap year.")
    except:
        print("Invalid number.")
