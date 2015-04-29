class Gigasecond

  ONE_BILLION = 10**9
  SECONDS_PER_DAY = 60 * 60 * 24

  def self.from(from)
    from + seconds_to_days(ONE_BILLION)
  end

  private

  def self.seconds_to_days(seconds)
    seconds / SECONDS_PER_DAY
  end
end
