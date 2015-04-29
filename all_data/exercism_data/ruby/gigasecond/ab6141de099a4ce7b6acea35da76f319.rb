class Gigasecond
  attr_reader :birthdate
  def initialize(birthdate)
    @birthdate = birthdate
    @gs_days = 11574
  end

  def date
    @birthdate + @gs_days
  end
end
