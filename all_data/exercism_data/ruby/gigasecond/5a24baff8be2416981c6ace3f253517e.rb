class Gigasecond
  gs_in_days = 10**9/86400
  def self.from(date)
    date + gs_in_days
  end
end
