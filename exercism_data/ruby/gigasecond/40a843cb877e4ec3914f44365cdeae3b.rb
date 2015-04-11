class Gigasecond
  def self.from(date_or_time)
    if date_or_time.class == Time
      return (date_or_time + 10**9).to_date
    end

    days_in_a_gigasecond = 10**9 / 60 / 60 / 24
    date_or_time + days_in_a_gigasecond
  end
end
