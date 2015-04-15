require 'prime'

class Prime
  def self.nth(spot)
    Prime.each_with_index do |num, index|
      raise ArgumentError if spot == 0
       return num if index == spot - 1
    end
  end
end
