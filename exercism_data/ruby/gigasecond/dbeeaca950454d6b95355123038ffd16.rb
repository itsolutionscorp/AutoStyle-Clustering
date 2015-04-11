module Gigasecond
  extend self

  def from(date)
    date + days
  end

  private

  def days
    10**9/60/60/24
  end
end
