class Gigasecond
  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    @birth_date + date_to_seconds
  end

  private

  def date_to_seconds
    1000000000 / 60 / 60 / 24
  end
end
