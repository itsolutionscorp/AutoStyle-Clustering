require 'date'

class Gigasecond

  def self.from(start_date)
    days_in_a_giga_second = (10**9)/60/60/24
    start_date = start_date
    end_date = start_date + days_in_a_giga_second
  end
 
end
