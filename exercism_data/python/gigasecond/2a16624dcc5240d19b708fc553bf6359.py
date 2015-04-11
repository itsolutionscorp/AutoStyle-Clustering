import datetime
from datetime import date

def add_gigasecond(birth_date):

	ordinal_birth_date = datetime.date.toordinal(birth_date)

	gigasecond_in_days = ((10.0 ** 9)/3600)/24

	ordinal_Gs_anniversary = ordinal_birth_date + gigasecond_in_days
	Gs_anniversary = datetime.date.fromordinal(int(ordinal_Gs_anniversary))

	return Gs_anniversary
