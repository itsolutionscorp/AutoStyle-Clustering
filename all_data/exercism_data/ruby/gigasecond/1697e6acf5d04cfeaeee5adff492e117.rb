class Gigasecond
  def self.from(date)
    return date + (10**9)/60/60/24 if date.class == Date
    gsa = date + (10**9)
    Date.new(gsa.year, gsa.month, gsa.day)
  end
end
