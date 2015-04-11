require 'prime'

class Raindrops
  def self.convert(num)
    conversion = ""
    conversion << 'Pling' if prime_factors(num).include?(3)
    conversion << 'Plang' if prime_factors(num).include?(5)
    conversion << 'Plong' if prime_factors(num).include?(7)

    return conversion unless conversion.empty?
    num.to_s
  end

  def self.prime_factors(number)
    Prime.prime_division(number).collect {|x| x.first}
  end
end
