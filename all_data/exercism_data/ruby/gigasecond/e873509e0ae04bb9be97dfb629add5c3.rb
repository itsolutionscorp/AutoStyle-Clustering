class Gigasecond
  def self.from(date)
    Date.jd(date.jd + (10**9)/(24*3600) )
  end
end
