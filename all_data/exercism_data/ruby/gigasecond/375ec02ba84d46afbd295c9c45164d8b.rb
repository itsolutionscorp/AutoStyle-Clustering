require 'date'

class Gigasecond

  def initialize birthday
    raise ArgumentError, "must provide a Date instance" unless birthday.is_a? Date
    @birthday = birthday
  end

  def date
    Time.at(@birthday.to_time.to_i + 10**9).to_date
  end

end
