require 'date'
require 'time'

class Gigasecond

  def initialize(birthday)

  @birthdate = birthday

  end

  def date

  Time.at(@birthdate.strftime('%s').to_i + (10**9)).to_date+1

  end

end
