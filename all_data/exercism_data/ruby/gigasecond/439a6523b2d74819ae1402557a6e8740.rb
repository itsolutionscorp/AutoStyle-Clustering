# Class to calculate the date someone turned their 1 gigasecond anniversary
class Gigasecond

  # Calculate the future date after 1 gigasecond from a given date
  def self.from(date)

    # 1 day is 86400 seconds
    days = 10**9 / 86400

    date + days
  end
end
