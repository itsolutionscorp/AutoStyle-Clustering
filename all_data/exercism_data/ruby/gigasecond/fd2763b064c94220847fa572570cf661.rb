class Gigasecond
  SECONDS = 10**9
  DAYS = SECONDS / 60 / 60 / 24

  def self.from(date_or_time)
    (date_or_time + gigasecond_for(date_or_time)).to_date
  end

  def self.gigasecond_for(date_or_time)
    case date_or_time
    when Date; DAYS
    when Time; SECONDS
    end
  end
end
