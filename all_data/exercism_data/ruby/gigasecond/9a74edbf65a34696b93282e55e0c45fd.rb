class Gigasecond
  attr_reader :date

  def initialize b
    @date = (b.to_time + 10**9).to_date
  end
end
