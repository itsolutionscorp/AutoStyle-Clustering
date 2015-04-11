module Gigasecond
  GIGASECOND = 10**9
  def self.from(date)
    seconds = date.to_time.to_i
    Time.at(seconds+GIGASECOND).to_date
  end
end
