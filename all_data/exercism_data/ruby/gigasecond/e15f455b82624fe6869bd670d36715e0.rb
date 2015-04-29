class Gigasecond
  attr_accessor :date

  def initialize(date)
    self.date = one_gigasecond_from(date)
  end

private
  def one_gigasecond_from(date)
    (date.to_time + 10**9).to_date
  end
end
