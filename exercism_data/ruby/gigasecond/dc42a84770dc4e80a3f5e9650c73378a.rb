require 'active_support/time'

class Gigasecond
  def self.from(from_date)
    from_date + 10**9.seconds
  end
end
