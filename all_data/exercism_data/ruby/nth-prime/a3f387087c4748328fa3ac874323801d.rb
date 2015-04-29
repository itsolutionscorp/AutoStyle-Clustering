require 'prime'
class Prime
  def self.nth(n)
    raise ArgumentError.new if n <= 0
    self.take(n).last
  end
end
