require 'prime'

class Prime
  def self.nth(num)
    raise ArgumentError, "Bad Index" unless num > 0
    p = Prime::EratosthenesGenerator.new
    p.first(num).last
  end
end
