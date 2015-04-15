require 'date'
class Gigasecond
  
  def self.from date
    Date.strptime((date + GIGASEC_IN_DAYS).strftime("%Y-%d-%m"),"%Y-%d-%m")
  end
  
  private
  GIGASEC_IN_DAYS = 11574.07361
  
end
