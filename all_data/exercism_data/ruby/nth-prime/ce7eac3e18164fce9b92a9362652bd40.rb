require 'prime'

class Prime
  def nth(n)
    raise ArgumentError if n == 0
    Prime.first(n)[-1]
  end
end
