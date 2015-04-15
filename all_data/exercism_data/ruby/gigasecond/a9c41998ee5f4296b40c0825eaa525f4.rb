require 'date'
require 'time'

class Gigasecond
  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    gs = 10**9
    min = 60
    hr = 60
    day = 24

    days = gs/(min*hr*day)

    @birth_date + days
  end

end
