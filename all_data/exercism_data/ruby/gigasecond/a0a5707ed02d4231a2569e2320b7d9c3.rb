class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    Time.at(anniversary_in_seconds).to_date
  end

  private

  def anniversary_in_seconds
    seconds + 1_000_000_000
  end

  def seconds
    @date.to_time.to_i
  end
end
