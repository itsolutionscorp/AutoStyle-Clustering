require 'prime'

class Prime
  def self.nth(n)
  	fail ArgumentError if n < 1
    (self.first n).last
  end
end
