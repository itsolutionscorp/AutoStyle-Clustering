require 'date'

class Gigasecond
  GIGASECOND = 10**9

  def initialize(date)
    @date_time = date.to_time
  end

  def birth_date
    subtract_gigasecond.to_date
  end

  def date
    add_gigasecond.to_date
  end

  private

  def add_gigasecond
    @date_time + GIGASECOND
  end

  def subtract_gigasecond
    @date_time - GIGASECOND
  end
end
