module Gigasecond
  GIGA_SECOND = 10**9

  module_function
  def from( time )
    time + GIGA_SECOND
  end
end
