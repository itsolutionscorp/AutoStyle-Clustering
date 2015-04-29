class Gigasecond
  def self.from(birthday)
    Time.at(birthday.to_time.to_i + 10**9).to_date
  end
end
