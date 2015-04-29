class Gigasecond
  GIGASECOND = 10**9
  GIGASECOND_IN_DAYS = 11574
  def self.from(date)
    if is_date_obj? date
      date + GIGASECOND_IN_DAYS
    elsif is_time_obj? date
      Date.parse((date + GIGASECOND).to_s)
    end
  end

  def self.is_date_obj? obj
    obj.class == Date
  end

  def self.is_time_obj? obj
    obj.class == Time
  end
end
