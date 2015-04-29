class Gigasecond
  attr_reader :birthday
  def initialize(birthday)
    @birthday = birthday
  end

  def date
    gigadate = birthday.to_time + 10**9
    gigadate.to_date
  end
end
