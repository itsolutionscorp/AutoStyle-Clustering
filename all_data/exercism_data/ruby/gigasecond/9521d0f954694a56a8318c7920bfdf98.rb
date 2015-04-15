class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    num_of_days_in_gs = (10**9)/86400
    @date + (num_of_days_in_gs)
  end
end
