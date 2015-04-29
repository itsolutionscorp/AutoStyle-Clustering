class Gigasecond
  def self.from (date)
    t = Time.parse(date.to_s) + 10**9
    return Date.parse(t.to_s)
  end
end
