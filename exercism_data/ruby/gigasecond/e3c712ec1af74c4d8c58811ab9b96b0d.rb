class Gigasecond
  DAYS_TO_GIGASECOND = 10**9 / 60 / 60 / 24
  def initialize(date_of_birth)
    @date_of_birth = date_of_birth
  end

  def date
    @date_of_birth + DAYS_TO_GIGASECOND
  end
end
