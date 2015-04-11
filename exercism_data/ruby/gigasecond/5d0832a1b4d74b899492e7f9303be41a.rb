require 'date'
require 'time'

class Gigasecond

  def initialize(birth_date)
    @birth_date = birth_date
  end

  def date
    anniversary = @birth_date.to_time.to_i + 10 ** 9
    Time.at(anniversary).to_date
  end
end
