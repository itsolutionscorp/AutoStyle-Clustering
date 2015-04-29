class Gigasecond

  def self.from(birthday)
    Time.at(birthday.to_i + (10**9)).utc
  end

end
