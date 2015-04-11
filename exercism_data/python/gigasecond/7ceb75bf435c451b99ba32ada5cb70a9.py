from datetime import timedelta


def add_gigasecond(date_of_birth):
    def get_gigasecond():
        return 10**9

    return date_of_birth + timedelta(seconds=get_gigasecond())    
