class Gigasecond
  DAYS_IN_A_GIGASECOND = 11574

  def initialize birth_date
    @birth_date = birth_date
  end

  def date
    gigasecond_birthday
  end

  private

  attr_reader :birth_date

  def gigasecond_birthday
    birth_date + DAYS_IN_A_GIGASECOND
  end
end
