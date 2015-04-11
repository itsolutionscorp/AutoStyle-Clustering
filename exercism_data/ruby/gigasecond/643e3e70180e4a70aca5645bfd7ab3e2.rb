class Gigasecond
  GIGASECOND = 10**9
  
  def initialize(source_date)
    @source_date = source_date
  end

  def date
    (@source_date.to_time + GIGASECOND).to_date
  end
  
  def self.from(date)
    Gigasecond.new(date)
  end
end
