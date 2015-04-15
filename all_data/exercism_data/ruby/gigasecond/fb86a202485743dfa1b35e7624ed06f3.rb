class Gigasecond
  GIGASECOND = 10 ** 9
  UNIXYEAR = 1977
  def self.from(date)
    gs_anniversary = date + GIGASECOND + (date.year > UNIXYEAR ? 0 : -3600)
    gs_anniversary.dst? ? gs_anniversary - 7200 : gs_anniversary
  end
end
