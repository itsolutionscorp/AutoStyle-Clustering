require 'prime'

class Raindrops
  def self.convert value
    factorization = Prime.prime_division(value)
    return value.to_s unless factorization.any?{|factor| [3,5,7].include?(factor[0])}
    raindrops = ""
    raindrops += "Pling" if factorization.any?{|factor| factor[0] == 3}
    raindrops += "Plang" if factorization.any?{|factor| factor[0] == 5}
    raindrops += "Plong" if factorization.any?{|factor| factor[0] == 7}
    raindrops
  end
end
