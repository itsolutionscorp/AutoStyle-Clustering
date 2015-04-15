require 'prime'

class Prime
  def nth(n)
    fail ArgumentError, 'Only n > 0 valid' unless n > 0

    Prime.take(n).last
  end
end
