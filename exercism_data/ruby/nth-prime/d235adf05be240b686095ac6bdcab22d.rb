require 'prime'
class Prime 
  def nth(num)
    raise ArgumentError if num < 1
    Prime.first(num)[-1]
  end
end
