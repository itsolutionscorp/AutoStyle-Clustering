require 'Date'

class Gigasecond

  def self.from(start_date)

    giga_seconds = 1000000000
    seconds_in_one_day = 86400


    start_date.to_date + (giga_seconds / seconds_in_one_day)


  end
end
