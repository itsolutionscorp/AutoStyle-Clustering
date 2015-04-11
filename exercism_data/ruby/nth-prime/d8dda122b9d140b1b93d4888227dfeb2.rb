require 'prime'

class Prime
  def self.nth(n)
    first(n).last or fail ArgumentError
  end
end
