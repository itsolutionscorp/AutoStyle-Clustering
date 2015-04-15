class Gigasecond
  def self.from(this_date)
    gs = (10**9) / (60 * 60 * 24)
    this_date + gs
  end
end
