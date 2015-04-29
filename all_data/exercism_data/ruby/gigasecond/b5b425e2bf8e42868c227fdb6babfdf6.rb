module Gigasecond

  def self.unit
    @unit ||= (10**9)
  end

  def self.from(date)
    (date.to_time + unit).to_date
  end
end
