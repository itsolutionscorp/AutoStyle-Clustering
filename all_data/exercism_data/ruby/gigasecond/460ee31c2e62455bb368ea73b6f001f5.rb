class Gigasecond
  GigaSecond = 1000000000
  attr_reader :date

  def initialize(birthdate)
    birthday_second = birthdate.to_time.to_i
    @date = Time.at(birthday_second + GigaSecond).to_date
  end
end
