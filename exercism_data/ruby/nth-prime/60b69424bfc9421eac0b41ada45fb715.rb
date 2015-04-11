require 'prime'

class Prime
  def self.nth(n)
    fail ArgumentError, "Invalid argument for method \"nth\"" if n < 1

    Prime.take(n)[-1]
  end
end
