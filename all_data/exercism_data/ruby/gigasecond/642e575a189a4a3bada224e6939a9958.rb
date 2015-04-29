require 'date'
require 'time'
class Gigasecond
  
  def self.from(brithday_date)
    brithday_date + (10**9)
  end
  
end

# Time.at(i)
# to_i for from
# January 1st, 1970
# Time.utc(1970,1,1) == 0
# (10**9) seconds
