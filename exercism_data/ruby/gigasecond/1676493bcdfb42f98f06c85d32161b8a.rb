class Gigasecond
  attr_reader :date
  class << self
    def from(date)
      new(date)
    end
  end

  def initialize(date)
    @date = date + (10**9  / 86400)
  end

end
