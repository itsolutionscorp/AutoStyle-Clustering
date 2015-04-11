class Gigasecond
  def self.from(birth_date)
    Time.at(birth_date.to_i + 10 ** 9)
  end
end
