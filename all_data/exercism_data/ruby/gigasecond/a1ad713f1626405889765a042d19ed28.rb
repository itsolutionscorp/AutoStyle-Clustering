require 'pry'
class Gigasecond
  def initialize(birthday)
    @birthdate = birthday
  end

  def date
    return @birthdate+days_in_gs
  end

  def days_in_gs
    gigasecond = 10**9
    return gigasecond/(24*60*60)
  end
end
