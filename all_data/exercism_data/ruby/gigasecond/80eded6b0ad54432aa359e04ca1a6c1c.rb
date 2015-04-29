require 'date'
require 'time'

class Gigasecond

  def self.from(date)
    # Add to date the gigasecond calculation:
    # 1 - Calculate the Gigaseconds (10**9)
    # 2 - Calculate how many seconds there are in a day
    # 3 - Divide the Gigaseconds by the seconds per day to 
    #     add to the given date
    date + (10**9) / (24*60*60)
  end
end
