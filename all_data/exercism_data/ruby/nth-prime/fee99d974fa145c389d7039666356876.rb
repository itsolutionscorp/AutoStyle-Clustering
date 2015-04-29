require 'prime'

class Prime
  def self.nth(n)
    raise ArgumentError.new if n <= 0
    self.first(n).last
  end
end
