class Gigasecond

  def self.from( date )
    date + days
  end

  def self.days
    gigasecond = 10**9
    seconds_of_day = 24*60*60
    gigasecond / seconds_of_day
  end

end
