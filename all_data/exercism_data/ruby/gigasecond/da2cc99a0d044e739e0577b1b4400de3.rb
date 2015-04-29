class Gigasecond
  def self.from(birthday)
    giga_anniversary = birthday.to_time + 10**9
    giga_anniversary.to_date
  end
end
