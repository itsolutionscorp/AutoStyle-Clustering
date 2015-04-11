class Gigasecond

  attr_accessor :birthdate

  def initialize(birthdate)
    @birthdate = birthdate
  end

  def date
    (@birthdate.to_time + 1_000_000_000).to_date
  end

end
