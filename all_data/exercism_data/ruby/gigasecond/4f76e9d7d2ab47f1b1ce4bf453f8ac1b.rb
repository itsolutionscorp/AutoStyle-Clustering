class Gigasecond
  def self.from(start_date)
    Gigasecond.new(start_date + (1000000000/3600/24))
  end

  attr_reader :date

  def initialize(date)
    @date = date
  end

end
