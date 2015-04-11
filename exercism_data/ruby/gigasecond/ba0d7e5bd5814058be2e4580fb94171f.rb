require 'date'
require 'time'

class Gigasecond
  attr_reader :birthday

  def initialize(birthday)
    @birthday = birthday
  end

  # Return the date where this person will be 10**9 seconds old
  def date
    (birthday.to_time + 10**9).to_date
  end
end
