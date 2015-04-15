require 'prime'

class Prime
  def nth(n)
    raise ArgumentError, "n must be greater or equal 1" if n < 1

    take(n).last
  end
end
