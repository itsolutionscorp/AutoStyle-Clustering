class Gigasecond
  attr_reader :date
  
  SECONDS = 10**9
  DAYS = SECONDS/(60*60*24)

  def self.from(date)
    @date = date + DAYS
  end
end
