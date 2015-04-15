class Gigasecond
  def self.from(birth_time)
    Time.at(birth_time.to_time.to_i + 1_000_000_000).to_date
  end
end
