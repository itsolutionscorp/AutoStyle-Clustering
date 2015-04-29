require 'date'
require 'time'

#Checking class type is ugly. Is there a way to use duck typing?
class Gigasecond
  #Add number of days in a gigasecond
  def self.from(date_time)
    date_time = date_time.to_time
    date_time = date_time + (10**9)
    Date.new(date_time.year, date_time.month, date_time.day)
  end
end
