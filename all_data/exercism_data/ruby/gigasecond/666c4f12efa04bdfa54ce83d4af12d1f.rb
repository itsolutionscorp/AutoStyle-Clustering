class Gigasecond
  attr_reader :date
  
  SECONDS = 10**9
  DAYS = SECONDS/(60*60*24)

  def initialize(date)
    @date = date + DAYS
  end
end
