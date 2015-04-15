class Gigasecond
  def self.from(date)
    gs = 10**9/86400
    date + gs
  end
end
