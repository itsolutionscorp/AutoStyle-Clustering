class Gigasecond
  def self.from(time)
    in_seconds = Time.new(time.year,time.month,time.day,time.hour,time.min,time.sec).to_i if time.class == Time
    in_seconds = Time.new(time.year,time.month,time.day).to_i if time.class == Date

    new_time = Time.at(in_seconds + 1000000000)
    new_year = new_time.year
    new_month = new_time.month
    new_day = new_time.day

    Date.new(new_year, new_month, new_day)
  end
end
