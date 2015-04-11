require 'prime'

class Prime
  def self.nth(arg)
    (arg > 0) ? Prime.take(arg).last : raise(ArgumentError)
  end
end
