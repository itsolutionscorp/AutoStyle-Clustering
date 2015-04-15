class Gigasecond
  def self.from(date)
    gs = 10**9
    gs_in_days = gs / 60 / 60 / 24
    date + gs_in_days
  end
end
