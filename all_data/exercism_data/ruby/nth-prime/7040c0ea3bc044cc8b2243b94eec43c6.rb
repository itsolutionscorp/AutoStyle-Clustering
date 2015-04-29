require 'prime'

class Prime
  def nth(n)
    raise ArgumentError, 'Argument Error' unless n >= 1
    Prime.first(n).last
  end
end
