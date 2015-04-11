class Gigasecond
  attr_reader :date
  
  @@gigasecond = 10**9
  @@days = @@gigasecond/(60*60*24)

  def self.from(date)
    @date = date + @@days
  end
end
