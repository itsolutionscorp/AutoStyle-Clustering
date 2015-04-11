require 'date'

class Gigasecond
  def initialize(birthday)
    @birthday = birthday
  end

  def date
    Date.strptime(seconds.to_s, '%s')
  end

  private

  def seconds
    @birthday.strftime('%s').to_i + 10**9
  end
end
