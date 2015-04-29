class Gigasecond
  ONE_GIGASECOND = 10 ** 9

  def self.from(birthday)
    seconds = ONE_GIGASECOND + birthday.to_time.to_i
    Time.at(seconds).to_date
  end
end
