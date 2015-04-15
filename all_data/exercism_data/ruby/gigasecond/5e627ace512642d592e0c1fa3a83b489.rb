class Gigasecond
  def self.from(date)
    gigasecond = 10**9 # seconds
    gs_in_days = gigasecond / 3600 / 24 # days
    date + gs_in_days 
  end
end
