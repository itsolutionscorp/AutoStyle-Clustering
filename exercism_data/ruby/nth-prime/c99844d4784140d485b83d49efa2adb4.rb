require 'prime'

class Prime
  def self.nth(number)
    raise ArgumentError if number == 0
    p = Prime.new
    (number-1).times do
      p.next
    end
    p.next
  end
end
