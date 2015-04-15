# Gigasecond
# This class will return the DateTime  1 Gigaseconds from the  +time+ entered
class Gigasecond

  # Adds 1000,000,000 seconds to the UTC time entered
  # Based on: http://ruby-doc.org//core-1.9.3/Time.html#method-i-2B
  # Coerces time to UTC format before performing arithmetic on it
  def self.from(time)
    return time + 10**9 if time.utc?
    return time.utc + 10**9
  end
end
