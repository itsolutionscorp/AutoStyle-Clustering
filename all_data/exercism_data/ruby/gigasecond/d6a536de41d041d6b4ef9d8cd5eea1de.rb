module Gigasecond

  @unit ||= (10**9)

  def self.from(date)
    (date.to_time + @unit).to_date
  end
end
