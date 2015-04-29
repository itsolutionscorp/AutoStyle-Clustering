class Gigasecond
  GIGASECOND = 1_000_000_000

  def initialize(birthday)
    @birthday = birthday
  end

  def date
    (@birthday.to_time + GIGASECOND).to_date
  end
end
