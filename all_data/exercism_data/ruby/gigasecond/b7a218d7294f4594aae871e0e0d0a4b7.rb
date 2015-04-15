# calculate date that is 1 gigasecond after supplied date
class Gigasecond
    def self.from(a_date)
	# make sure argument is a Date
	return nil unless a_date.is_a?(Date)
	
	# calculate number of days in a gigasecond (11,574 days)
	days = (10 ** 9) / 60 / 60 / 24
	
	# return a_date + days
	return a_date + days
    end
end
