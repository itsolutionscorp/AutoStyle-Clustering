#*****
#     Gigasecond
#     converts birthdays to 1 Gs Anniverasry

require 'date'
require 'time'

class Gigasecond

	def self.from(date)
		@birth_date = date
		@gigasecond = 10 ** 9

		#convert gigasecond into number of days
		mm, ss = @gigasecond.divmod(60)
		hh, mm = mm.divmod(60)
		dd, hh = hh.divmod(24)
		@gigasecond_to_days = dd
		
		@gs_anniversary_date = @birth_date + @gigasecond_to_days

        return @gs_anniversary_date
		
	end

end
