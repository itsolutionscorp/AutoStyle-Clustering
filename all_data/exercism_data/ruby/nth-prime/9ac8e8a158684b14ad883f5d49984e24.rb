require 'prime'
class Prime
  def self.nth(n)
    raise ArgumentError if n <= 0
    Prime.take(n).pop
  end
end
