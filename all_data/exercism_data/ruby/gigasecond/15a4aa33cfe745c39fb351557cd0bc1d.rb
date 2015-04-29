module Gigasecond
  def self.unit
    @unit ||= (10**9)
  end
  def self.from(date)
    # Convert date to seconds ( since epoch ), add 10**9 and convert back to
    # date
    Date.parse(Time.at(Time.parse(date.to_s).to_i + unit).to_s)
  end
end
