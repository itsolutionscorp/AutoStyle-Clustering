class Gigasecond
  def self.from(date)
    gs_anniversary = date + 10**9 + (date.gmt_offset - Time.now.gmt_offset)
    gs_anniversary.dst? ? gs_anniversary - 3600 : gs_anniversary
  end
end
