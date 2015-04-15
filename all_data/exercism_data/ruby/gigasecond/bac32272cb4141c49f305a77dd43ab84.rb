class Gigasecond
  ONE_GIGASECOND = 10**9

  def self.from(birth_time)
    birthday_unix_time = birth_time.to_i + ONE_GIGASECOND
    Time.at(birthday_unix_time).utc
  end
end
