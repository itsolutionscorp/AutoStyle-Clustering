module Gigasecond
  def self.unit
    @unit ||= (10**9)
  end 
  # Converts date to seconds ( since epoch ), 
  # adds 10**9 and converts back to date.
  def self.from(date)
    Date.parse(Time.at(Time.parse(date.to_s).to_i + unit).to_s)
  end
end
