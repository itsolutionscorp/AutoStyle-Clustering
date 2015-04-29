require 'prime'
require 'pry'

class Prime
  def nth(n)
    raise ArgumentError if n == 0
    self.first(n).last
  end
end
