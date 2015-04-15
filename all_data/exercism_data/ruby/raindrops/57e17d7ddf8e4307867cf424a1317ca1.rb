# - If the number contains 3 as a prime factor, output 'Pling'.
# - If the number contains 5 as a prime factor, output 'Plang'.
# - If the number contains 7 as a prime factor, output 'Plong'.
# - If the number does not contain 3, 5, or 7 as a prime factor,
#   just pass the number's digits straight through.
#
# ## Examples
# - 28's prime-factorization is 2, 2, 7.
# - In raindrop-speak, this would be a simple "Plong".
# - 1755 prime-factorization is 3, 3, 3, 5, 13.
# - In raindrop-speak, this would be a "PlingPlang".
# - The prime factors of 34 are 2 and 17.
# - Raindrop-speak doesn't know what to make of that,
#   so it just goes with the straightforward "34".
class Raindrops
  def self.convert num
    return "1" if num == 1
    answer = ""
    ((prime_factors(num).uniq) & [3, 5, 7]).each do |factor|
      case factor
        when 3 then answer << "Pling"
        when 5 then answer << "Plang"
        when 7 then answer << "Plong"
      end
    end
    answer << "#{num}" if answer == ""
    answer
  end

  protected
  def self.prime_factors(num)
    return [] if num == 1
    factor = (2..num).find {|x| num % x == 0} 
    [factor] + prime_factors(num / factor) 
  end
end
