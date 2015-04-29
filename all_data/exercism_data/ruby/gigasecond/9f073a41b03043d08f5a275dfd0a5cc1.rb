require 'date'
require 'time'

class Gigasecond

  def self.from(from_date)
  	raise ArgumentError, "Date is null" if from_date.nil?
	date_in_seconds = from_date.strftime('%s').to_r
	date_in_seconds += 1000000000

	new_date = Time.at(date_in_seconds).getgm
	Date.new(new_date.year, new_date.month, new_date.day)
  end

end
