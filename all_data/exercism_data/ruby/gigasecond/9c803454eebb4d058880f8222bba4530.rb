class Gigasecond
  def self.from(birth_time)
    date = birth_time.to_time + 10**9
    date.to_date
  end
end
