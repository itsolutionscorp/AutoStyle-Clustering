class Gigasecond
  def initialize birth_date
    @birth_date = birth_date
  end

  def date
    @birth_date + (10**9 / 60 / 60 / 24)
  end
end
