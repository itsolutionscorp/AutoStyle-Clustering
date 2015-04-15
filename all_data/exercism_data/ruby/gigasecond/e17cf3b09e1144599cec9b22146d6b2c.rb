class Gigasecond
  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    gigsecond_birth = @birth_date.to_time + 10**9
    gigsecond_birth.to_date
  end
end
