class Gigasecond  
    GS = (10**9)
  def self.from (start_date)
    (start_date.to_time + GS).to_date
  end
end
