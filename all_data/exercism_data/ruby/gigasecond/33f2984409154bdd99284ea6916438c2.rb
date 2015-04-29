class Gigasecond
  def self.from(date)
    date + gs_to_days
  end
  
  def self.gs_to_days
    11574
  end
end
