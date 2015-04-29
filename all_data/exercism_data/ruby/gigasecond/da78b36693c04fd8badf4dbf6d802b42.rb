require 'date'

class Gigasecond
  def self.from(date_or_time)
    date_or_time = date_or_time.to_time if date_or_time.respond_to?(:rfc3339)
    gigasecond_time = date_or_time + (10**9)
    gigasecond_time.to_date
  end
end
