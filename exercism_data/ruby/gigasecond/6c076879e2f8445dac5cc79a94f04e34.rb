class Gigasecond
  
  Gs_to_day = 10**9 / 60 / 60 / 24
  
  def self.from(date_from)
    date_from + Gs_to_day
  end
end
