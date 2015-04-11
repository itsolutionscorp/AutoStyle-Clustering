require 'prime'

class Raindrops
  def convert(number)
    result = number.prime_factors
                   .map { |prime_factor| RAINDROP_MESSAGES[prime_factor] }
                   .join

    result.empty?? number.to_s : result
  end

  private

  RAINDROP_MESSAGES = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }
end

class Integer
  def prime_factors
    self.prime_division.map(&:first)
  end
end
