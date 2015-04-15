require 'prime'

class Prime
  def self.nth n
    raise ArgumentError if n < 1
    first(n).last
  end
end
