class Gigasecond
  def initialize(input_date)
    @DAYS_IN_A_BILLION_SECONDS = 11574
    @date = input_date
  end

  def date
    @date + @DAYS_IN_A_BILLION_SECONDS
  end
end
