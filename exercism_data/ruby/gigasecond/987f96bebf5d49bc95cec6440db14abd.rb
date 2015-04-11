class Gigasecond

  def self.from(date)
    one_gigasecond = 10**9
    seconds_in_day = 24*60*60
    date + (one_gigasecond / seconds_in_day)
  end

end
