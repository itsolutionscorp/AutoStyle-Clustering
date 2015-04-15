class Gigasecond
  def self.from(from_date)
    one_day = 24 * 60 * 60 #number of seconds in a day
    from_date + 10**9/one_day
  end
end
