class Gigasecond
  GIGASECOND = 1_000_000_000

  def initialize(date_of_birth)
    @born_on = date_of_birth
  end

  def date
    (@born_on.to_time + GIGASECOND).to_date
  end

end
