require 'prime'

class Prime
  def self.nth(nth)
    nth > 0 ? Prime.take(nth).last : raise(ArgumentError)
  end
end
