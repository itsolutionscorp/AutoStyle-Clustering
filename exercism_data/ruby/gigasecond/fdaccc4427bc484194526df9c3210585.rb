class Gigasecond
  def self.from(birthday)
    Time.at(birthday.to_time.to_i + 1_000_000_000).to_date
  end
end
