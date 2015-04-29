require 'date'

class Gigasecond
  ONE_GIGASECOND = 10 ** 9

  def self.from(date_or_time)
    (date_or_time.to_time + ONE_GIGASECOND).to_date
  end
end
