require 'prime'

class Prime
  def self.nth(n)
    raise ArgumentError if n.zero?
    result = 0
    test = 0
    while test != n 
      result += 1
      test += 1 if Prime.prime?(result)
    end
   result 
  end
end
