class Gigasecond
  def self.from(date)
    gs_in_days = ((10**9)*1.15741e-5).round
    date + gs_in_days
  end
end
