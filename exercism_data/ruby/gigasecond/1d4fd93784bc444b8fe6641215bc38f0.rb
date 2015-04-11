class Gigasecond  
  def self.from (start_date)
    gs_in_days = (10**9)/24/60/60
    start_date + gs_in_days
  end
end
