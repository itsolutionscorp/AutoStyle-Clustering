class Gigasecond
  attr_reader :date

  def initialize(date)
    @date = date + one_gigasecond / seconds_in_a_day
  end

  def one_gigasecond
    10**9
  end

  def seconds_in_a_day
    (60 * 60 * 24)
  end
end
