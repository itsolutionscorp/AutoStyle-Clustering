class Gigasecond
  def initialize(source_date)
    @source_date = source_date
  end

  def date
    (@source_date.to_time + 10**9).to_date
  end
end
