from datetime import timedelta

gigadays = ((((10**9)/60.0)/60.0)/24.0)

def add_gigasecond(idate):
	return idate + timedelta(days=gigadays)
