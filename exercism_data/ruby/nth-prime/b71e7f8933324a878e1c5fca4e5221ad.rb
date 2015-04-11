require 'prime'

class Prime
  def self.nth(i)
  	fail ArgumentError if (i < 1)
    (Prime.first i).last
  end
end
