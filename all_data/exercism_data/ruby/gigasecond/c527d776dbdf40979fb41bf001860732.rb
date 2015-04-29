class Gigasecond 
  ONEGEE_AS_DAYS = 10**9 

  def self.from(date_or_time)
    time = date_or_time.to_time
    (time + ONEGEE_AS_DAYS).to_date
  end
end
