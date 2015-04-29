require 'date'

class Gigasecond

  GIGA_SECOND = 10**9

  def self.from(input_date)
    (input_date.to_time + GIGA_SECOND).to_date
  end

end
