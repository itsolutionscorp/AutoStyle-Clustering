class Gigasecond
  def initialize(date)
    @date = date
  end

  def date
    @date + (10**8)/360/24
  end
end
