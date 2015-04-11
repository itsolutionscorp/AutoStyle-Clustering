class Gigasecond
  def self.from(birth_date)
    (birth_date.to_time + 10**9).to_date
  end
end
