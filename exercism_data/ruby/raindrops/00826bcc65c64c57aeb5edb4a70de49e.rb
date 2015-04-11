# class Raindrops
#
#   def self.convert(arg)
#     raindrops = []
#     prime_numbers = [3,5,7]
#     while arg%3 == 0 || arg%5 == 0 || arg%7 == 0 do
#       prime_numbers.each { |prime| raindrops << prime && arg /= prime if arg%prime == 0}
#     end
#     if raindrops == []
#       arg.to_s
#     else
#       raindrops.uniq.join.gsub(/[357]/, "3"=> 'Pling', "5"=> 'Plang', "7"=> 'Plong')
#     end
#   end
# end
# you can use .map instead of .each and use the returned array (return.compact != [] ) to stop the loop. However, this is harder to read.

class Raindrops

  def self.convert(arg)
    divisible = ["!empty || !nil"]
    raindrops = []
    prime_numbers = [3,5,7]
    while divisible.compact != [] do
      divisible = prime_numbers.map { |prime| raindrops << prime && arg /= prime if arg%prime == 0}
    end
    if raindrops == []
      arg.to_s
    else
      raindrops.uniq.join.gsub(/[357]/, "3"=> 'Pling', "5"=> 'Plang', "7"=> 'Plong')
    end
  end
end
