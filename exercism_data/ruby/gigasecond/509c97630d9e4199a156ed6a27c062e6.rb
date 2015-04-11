require 'date'
class Gigasecond
  
  def self.from date
    date + GIGASEC_IN_DAYS
  end
  
  private
  GIGASEC_IN_DAYS = 11574
  
end
