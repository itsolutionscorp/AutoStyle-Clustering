class Gigasecond
  def self.from(date)
    gs_in_days = 10**9 / 60 / 60 / 24
    anniversary = date + gs_in_days
  end
end
