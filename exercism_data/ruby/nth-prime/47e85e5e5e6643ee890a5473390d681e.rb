require 'prime'

class Prime

  def self.nth(n)
  	raise ArgumentError unless n > 0
    self.first(n).last
  end

end
