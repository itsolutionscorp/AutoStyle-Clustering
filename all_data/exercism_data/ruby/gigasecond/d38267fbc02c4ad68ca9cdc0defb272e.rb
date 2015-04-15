class Gigasecond
  def self.from(birthdate)
    Time.at(birthdate.to_i + 10**9)
  end
end
