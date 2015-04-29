require 'prime'

class Prime
  def nth num
    raise ArgumentError, 'Argument has to be positive integer!' unless num > 0
    Prime.first(num).last
  end
end
