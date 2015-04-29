module Gigasecond
  extend self

  def from(date)
    date + days
  end

  private

  def days
    seconds/60/60/24
  end

  def seconds
    10**9
  end
end
