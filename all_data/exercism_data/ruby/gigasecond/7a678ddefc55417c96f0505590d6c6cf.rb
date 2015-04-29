class Gigasecond
  attr_accessor :date

  def self.from(start_date)
    new((start_date.to_time + 1_000_000_000).to_date)
  end

  def initialize(gigasecond_date)
    @date = gigasecond_date
  end
end
