module Gigasecond
  GIGASECOND = 10**9
  IN_MINUTES = GIGASECOND / 60
  IN_HOURS = IN_MINUTES / 60
  IN_DAYS = IN_HOURS / 24

  module_function

  def from(date)
    date + IN_DAYS
  end
end
