class Gigasecond
  def self.from(date)
    GigasecondDate.new(date).advance_one_gigasecond
  end
end

class GigasecondDate
  ONE_GIGASECOND = 10 ** 9
  SECONDS_IN_DAY = 86400

  def initialize(start_date)
    @start_date = start_date
  end 

  def advance_one_gigasecond
    start_date + secs_to_days(ONE_GIGASECOND)
  end

  private
  attr_reader :start_date

  def secs_to_days(seconds)
    seconds / SECONDS_IN_DAY
  end
end
