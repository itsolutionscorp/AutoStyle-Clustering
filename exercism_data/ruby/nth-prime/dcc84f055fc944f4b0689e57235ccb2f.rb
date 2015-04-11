require 'prime'

class Prime
  def nth(n)
    fail ArgumentError, "#{n} for (1..+)" if n < 1
    Prime.take(n).last
  end
end
