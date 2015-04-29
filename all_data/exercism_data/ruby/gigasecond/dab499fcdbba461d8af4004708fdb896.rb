class Gigasecond
  SECONDS = 10**9

  def self.from(birthday)
    (birthday.to_time + SECONDS).to_date
  end
end
