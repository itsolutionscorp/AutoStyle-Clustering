#
# Given a date, calculate the date that is one billion seconds later.
#
module Gigasecond

  GIGASECOND = 10 ** 9

  def self.from(date)
    (date.to_time + GIGASECOND).to_date
  end

end
