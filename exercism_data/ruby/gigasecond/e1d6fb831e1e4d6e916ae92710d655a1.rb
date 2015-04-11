class Gigasecond

  def self.from(date)
    gigaseconds = 10**9
    seconds_in_day = 24*60*60
    days = gigaseconds / seconds_in_day
    date + days
  end

end
