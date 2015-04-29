class Gigasecond

  attr_reader :born_at
  def initialize(start_date)
    @born_at = start_date.to_time
  end

  def date
    (born_at + 10**9).to_date
  end

end
