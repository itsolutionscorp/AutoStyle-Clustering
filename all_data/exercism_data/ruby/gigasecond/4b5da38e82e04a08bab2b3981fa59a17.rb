module Gigasecond

  def self.from(date)
    gigasecond_in_days = 1000000000 / 24 / 60 / 60
    date + gigasecond_in_days
  end

end
