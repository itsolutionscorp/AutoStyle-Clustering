class Gigasecond
  def self.from(date)
    seconds = date.strftime("%s").to_i
    g_seconds = (seconds + 1_000_000_000).to_s
    Date.strptime(g_seconds, "%s")
  end
end
