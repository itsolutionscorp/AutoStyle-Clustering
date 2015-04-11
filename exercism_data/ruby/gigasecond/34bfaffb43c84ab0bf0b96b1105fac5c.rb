module Gigasecond

  def self.gigasecond
    10 ** 9
  end

  def self.from(birthday)
    gs_anniversary = birthday.to_time + self.gigasecond
    gs_anniversary.to_date
  end

end
