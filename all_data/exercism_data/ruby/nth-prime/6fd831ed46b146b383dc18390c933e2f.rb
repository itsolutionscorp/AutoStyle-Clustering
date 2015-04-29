require 'prime'

class Prime
  def nth(n)
    raise ArgumentError if n == 0

    first(n).last
  end
end
