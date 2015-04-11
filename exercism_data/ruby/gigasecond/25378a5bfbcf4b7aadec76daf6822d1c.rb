require 'time'
require 'date'
class Gigasecond

  GIGASECOND = 10 ** 9 
  GIGASECOND_AS_DAYS = GIGASECOND / 60 / 60 / 24

  def self.from(date)  
    if date.is_a? Time
      (date + GIGASECOND).to_date
    else
      date + GIGASECOND_AS_DAYS
    end
  end
end
