require 'prime'

class Prime
  def nth(num)
    raise ArgumentError if num <= 0
    Prime.first(num).last
  end
end
