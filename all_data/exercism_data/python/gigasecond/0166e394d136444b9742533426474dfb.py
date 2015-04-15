def add_gigasecond(birthDate):
    from datetime import timedelta
    # Given birthdayte calc addition of 10**9 seconds
    gigaSec = (10**9)
    return birthDate + timedelta(0,gigaSec)
