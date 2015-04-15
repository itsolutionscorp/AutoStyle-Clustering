class Gigasecond
  ANNIVERSARY_IN_DAYS = 11574

  def initialize(date_of_birth)
    @date_of_birth = date_of_birth
  end

  def date
    @date_of_birth + ANNIVERSARY_IN_DAYS
  end
end
