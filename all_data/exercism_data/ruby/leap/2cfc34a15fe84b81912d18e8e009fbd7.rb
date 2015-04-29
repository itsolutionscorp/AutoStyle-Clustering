require_relative 'divisible'

class Year
  include Divisible

  def initialize(year)
    @year = year
  end

  def self.leap?(year)
    new(year).leap? 
  end

  def leap?
    return true if divisible_by_four_hundred?(@year)
    return false if divisible_by_one_hundred?(@year)
    return true if divisible_by_four?(@year)
    return false
  end
end


# incase Exercism doesn't allow uploading multiple files
# this is the contents of the Divisible Module
# 
# module Divisible
#     def divisible_by?(number, divisor)
#       number % divisor == 0
#     end

#     def divisible_by_four_hundred?(number)
#       divisible_by?(number, 400)
#     end

#     def divisible_by_one_hundred?(number)
#       divisible_by?(number, 100)
#     end

#     def divisible_by_four?(number)
#       divisible_by?(number, 4)
#     end
# end
