require 'prime'

class Prime

  result = 0
  def self.nth(num)
    [1].first(-1) if num == 0
    primearray = Prime.first num
    result = primearray.last
  end
  result
end
