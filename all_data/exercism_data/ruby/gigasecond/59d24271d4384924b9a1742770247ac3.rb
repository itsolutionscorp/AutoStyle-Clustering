class Gigasecond
  def self.from(date)
    original_date = date.jd
    gs_in_days = 10**9 / 60 / 60 / 24
    anniversary = original_date + gs_in_days
    Date.jd(anniversary)
  end
end
