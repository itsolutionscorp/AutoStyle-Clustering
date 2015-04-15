module Gigasecond
  IN_SECONDS = 1_000_000_000
  IN_DAYS = IN_SECONDS / (3600 * 24)

  def self.from(date)
    date + IN_DAYS
  end
end
