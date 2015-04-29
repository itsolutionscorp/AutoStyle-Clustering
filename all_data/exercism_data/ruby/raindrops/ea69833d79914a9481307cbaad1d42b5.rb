require 'prime'

class Raindrops
  SOUNDS = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(num)
    factor_pairs = Prime.prime_division(num).each
    uniq_factors = []
    output = ""

    loop do
      factor_pairs.next.uniq.each { |digit| uniq_factors.push(digit) }
    end

    uniq_factors.uniq.each { |digit| output += SOUNDS[digit] unless SOUNDS[digit].nil? }

    return (output.length > 0 ? output : num.to_s)
  end
end

# puts Raindrops.convert(1)

# d = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
# t = [[2, 2], [3, 4]]
# # p Prime.prime_division(12)
# factor_pairs = Prime.prime_division(12).each
# a = Array.new
# loop do
#   factor_pairs.next.uniq.each {|di| a.push(di)}
# end

# s = String.new

# a.uniq.each do |num|
#   # s + d[num] unless d[num].nil?
#   s +=  d[num] unless d[num].nil?
# end

# puts s
