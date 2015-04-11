class Gigasecond

  def initialize(dateInput)
    @date = dateInput
  end

  def date()
    gigaSecondDate = Time.at(@date.to_time.to_i + 10**9)
    Date.new(gigaSecondDate.year, gigaSecondDate.month, gigaSecondDate.day)
  end

end
