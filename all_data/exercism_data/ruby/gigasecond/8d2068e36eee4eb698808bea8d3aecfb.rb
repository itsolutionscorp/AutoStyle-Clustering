# Calculate the day that will be ~ 1 gigasecond from the day provided
class Gigasecond
  @gigasecond_to_days = 1_000_000_000 / 86_400
  def self.from(date)
    date + @gigasecond_to_days
  end
end
