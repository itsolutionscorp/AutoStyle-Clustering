class Raindrops

  def self.convert(number)
    raindrops = ""
    if number % 3 == 0
      raindrops << "Pling"
    end
    if number % 5 == 0
      raindrops << "Plang"
    end
    if number % 7 == 0
      raindrops << "Plong"
    end
    if raindrops.empty?
      number.to_s
    else
      raindrops
    end
  end

end






#   def self.convert(number)
#     # if it is divisible by 3 and 5
#     # how do we return plingplang
#
#     #how do we check if it is divisible by both
#     # 3 and 5????
#
#   # case number
#   #   when three_and_five
#     if three_and_five?(number)
#
#     elsif five_and_seven?
#
#     elsif divisible_by_three
#
#     elsif divisible_by_five
#
#     elsif divisible_by_seven
#
#     else
#       "1"
#     end
#   end
#
#   def self.divisible_by_three
#     number % 3 == 0
#     "Pling"
#   end
#
#   def self.divisible_by_five
#     number % 5 == 0
#     "Plang"
#   end
#
#   def self.divisible_by_seven
#     number % 7 == 0
#     "Plong"
#   end
#
#   def self.three_and_five?(number)
#     number % 3 == 0 && number % 5 == 0
#     "PlingPlang"
#   end
#
#   def self.five_and_seven?
#     number % 5 == 0 && number % 7 == 0
#     "PlingPlong"
#   end
# end
