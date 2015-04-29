# Calculate the day that will be ~ 1 gigasecond from the day provided
class Gigasecond
  GIGASECOND_IN_DAYS = 1_000_000_000 / 86_400
  def self.from(date)
    date + GIGASECOND_IN_DAYS
  end
end
