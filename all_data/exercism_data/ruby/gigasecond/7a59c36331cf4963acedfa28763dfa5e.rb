class Gigasecond
  def self.from(start_date, days_out = ONE_BILLION_SECONDS_IN_DAYS)
    Gigasecond.new(start_date,days_out)
  end

  ONE_BILLION_SECONDS_IN_DAYS = 1000000000/3600/24

  attr_reader :date

  def initialize(date, days_out)
    @date = date + days_out
  end

end
