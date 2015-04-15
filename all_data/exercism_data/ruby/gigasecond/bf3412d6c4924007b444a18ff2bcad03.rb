class Gigasecond
  attr_reader :date
  def initialize(date)
    @date = date + (10**9 / (60*60*24))
  end
end
