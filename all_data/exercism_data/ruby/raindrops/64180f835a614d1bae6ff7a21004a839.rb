require "prime"

# PSUEDOCODE
# Input: integer
# Output:
# - If the number contains 3 as a prime factor, output 'Pling'.
# - If the number contains 5 as a prime factor, output 'Plang'.
# - If the number contains 7 as a prime factor, output 'Plong'.
# - If the number does not contain 3, 5, or 7 as a prime factor,
#   just pass the number's digits straight through.
# -----------------------------------------
# get array of unique prime factors of num
# create empty raindrops string
# for each prime in array
#   if num is 3 add Pling to string
#   elsif num is 5 add Plang to string
#   elsif num is 7 add Plong to string
#  string = num.to_s if string == ""
#  return string


class Raindrops
  def self.convert(num)
    primes = Prime.prime_division(num).flatten.uniq

    raindrops = ""

    primes.each do |prime|
      case prime
        when 3
          raindrops << "Pling"
        when 5
          raindrops << "Plang"
        when 7
          raindrops << "Plong"
        end
      end

    if raindrops.empty?
      raindrops = num.to_s
    end

    raindrops
  end

end
