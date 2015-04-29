module Gigasecond
  UNIT = (10**9) 
  # Converts date to seconds ( since epoch ), 
  # adds 10**9 and converts back to date.
  def self.from(date)
    Time.at(date.to_time.to_i + UNIT).to_date
  end
end
