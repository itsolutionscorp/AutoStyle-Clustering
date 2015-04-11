class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    num_of_days_in_gs = (10**9)/60/60/24
    @date + (num_of_days_in_gs)
  end
end
