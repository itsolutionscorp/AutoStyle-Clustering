class Gigasecond
  attr_reader :anniversary

  def initialize(input)
    @anniversary = input
  end

  def date
    anniversary_in_seconds = to_seconds(@anniversary)
    anniversary_in_seconds += 1_000_000_000
    Time.at(anniversary_in_seconds).to_date
  end

  private

  def to_seconds(date)
    date.to_time.to_f
  end
end
