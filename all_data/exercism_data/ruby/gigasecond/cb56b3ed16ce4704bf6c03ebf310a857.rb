class Gigasecond
  class << self
    alias_method :from, :new
  end

  attr_reader :date

  GIGASECOND = 10**9

  def initialize(bdate)
    @bdate = bdate 
    calculate!
  end

  private

  def calculate!
    @date = Time.at(@bdate.to_time + GIGASECOND).to_date
  end
end
