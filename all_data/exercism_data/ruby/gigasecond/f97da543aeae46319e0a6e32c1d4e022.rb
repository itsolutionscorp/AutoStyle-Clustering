class Gigasecond

  GIGASECONDS = 10**9

  def self.from(date)
    date_time = date.to_time
    gs_anniversary = Time.at(date_time.to_i + GIGASECONDS)
    Date.new(gs_anniversary.year, gs_anniversary.month, gs_anniversary.day)
  end

end
