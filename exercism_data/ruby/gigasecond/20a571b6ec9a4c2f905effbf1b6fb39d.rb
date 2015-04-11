require 'date'
require 'time'

#Checking class type is ugly. Is there a way to use duck typing?
class Gigasecond
  #Add number of days in a gigasecond
  def self.from(date_time)
    if date_time.kind_of? Date
      #Date class seems to default to adding minutes
      #86400 seconds in a day
      date_time = date_time + (10**9)/86400
    elsif date_time.kind_of? Time
      #Time class seems to default to adding seconds
      date_time = date_time + (10**9)
    end
    Date.new(date_time.year, date_time.month, date_time.day)
  end
end
