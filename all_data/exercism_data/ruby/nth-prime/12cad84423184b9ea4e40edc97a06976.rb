require 'prime'

class Prime
  def self.nth(num)
    raise ArgumentError if num == 0
    p = Prime::EratosthenesGenerator.new
    n = 0
    num.times do
      n = p.next
    end
    n
  end



end
