class Gigasecond
  attr_reader :date

  def initialize(date)
    @date = date + 11574
  end
end
