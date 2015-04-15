class Gigasecond
  def self.from(a)
    gs_1 = 10 ** 9
    gs = (a.to_time + gs_1).to_date
    gs
  end
end

