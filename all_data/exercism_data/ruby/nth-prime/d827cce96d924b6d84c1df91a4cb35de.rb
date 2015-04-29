require 'prime'

class Prime
  def self.nth(index)
    raise ArgumentError, 'index out of bound' if index <= 0
    first(index).last
  end
end
