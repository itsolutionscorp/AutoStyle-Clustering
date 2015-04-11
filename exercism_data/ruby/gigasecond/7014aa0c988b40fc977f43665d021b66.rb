require 'date'

class Gigasecond

  def initialize(date)
    @date=date
  end

  def date
    @date + gigasecond_in_days
  end

  def gigasecond_in_days
    ((1E9).to_i / 60 / 60 / 24)
  end

end
