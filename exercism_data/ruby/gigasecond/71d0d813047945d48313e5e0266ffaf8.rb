class Gigasecond
  def self.from(date_obj)
    time_obj = date_obj.to_time + 1000000000
    time_obj = time_obj.to_date
  end
end
