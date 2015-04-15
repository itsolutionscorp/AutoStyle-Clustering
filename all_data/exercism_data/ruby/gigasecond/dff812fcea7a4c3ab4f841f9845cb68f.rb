class Gigasecond
  GIGASECOND = 10**9
  def self.from(date)
    seconds_at_date = date.to_time.to_i
    seconds_at_gigasecond_aniversary = seconds_at_date + GIGASECOND
    Time.at(seconds_at_gigasecond_aniversary).to_date
  end
end
