class Gigasecond

  def self.from (date)
    #gigasecond = 10**9 seconds
    #86400 seconds in 1 day
    gs_in_days = 10**9 / 86400
    date + gs_in_days
    #could just write date + (10**9/86400)
  end
end
