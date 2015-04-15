class Gigasecond
  attr_reader :base_date
  def initialize(base_date)
    @base_date = base_date
  end

  def date
    Time.at( base_date_seconds_since_epoc + (10**9)).to_date
  end

  def base_date_seconds_since_epoc
    base_date.to_time.to_i
  end

end
