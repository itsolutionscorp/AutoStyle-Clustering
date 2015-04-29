require 'prime'

class Prime
  def nth(num)
    if num == 0
      raise ArgumentError.new('Argument < 1')
    end
    primeOut = Prime.take(num)
    return primeOut[-1]
  end
end
