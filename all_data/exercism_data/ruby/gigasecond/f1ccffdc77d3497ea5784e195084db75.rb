class Gigasecond

  GIGA_SECOND = 1_000_000_000

  def self.from(date)
    gs_time = Time.parse(date.to_s) + GIGA_SECOND
    Date.parse(gs_time.to_s)
  end

end
