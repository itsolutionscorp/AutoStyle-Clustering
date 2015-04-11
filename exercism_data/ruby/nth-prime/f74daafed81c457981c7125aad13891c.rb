require 'Prime'

class Prime
  def Prime.nth(n)
    if n == 0
      raise ArgumentError
    end
    Prime.take(n).last
  end
end
