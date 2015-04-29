module Gigasecond
  module_function
  def Gigasecond.from(date)
    (date.to_time + 10 ** 9).to_date
  end
end
