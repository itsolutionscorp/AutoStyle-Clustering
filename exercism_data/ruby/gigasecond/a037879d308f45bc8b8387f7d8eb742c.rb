class Gigasecond
  def initialize(birthdate)
    @birthdate = birthdate
  end

  def date
    @date ||= (@birthdate.to_time + 1_000_000_000).to_date
  end
end
