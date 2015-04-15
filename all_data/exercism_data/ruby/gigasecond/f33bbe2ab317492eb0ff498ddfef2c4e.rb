class Gigasecond
  attr_reader :date

  def initialize(date)
    @date = Time.at(date.to_time.to_i + 1_000_000_000).to_date
  end

end
