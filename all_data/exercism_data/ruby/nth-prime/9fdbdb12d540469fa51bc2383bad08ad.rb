require 'prime'

class Prime
  def self.nth(n)
    n < 1 and fail ArgumentError
    first(n).last
  end
end
