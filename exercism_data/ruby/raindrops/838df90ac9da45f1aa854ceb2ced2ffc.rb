require 'prime'
require 'pry'

class Raindrops
  def self.convert(int)
    result = build_result_from prime_factors_for int

    result_or_int_as_string(result, int)
  end

  private

  def self.prime_factors_for(i)
    i.prime_division.flatten
  end

  def self.build_result_from(prime_factors)
    # 3,5,7 / Pling,Plang,Plong
    result  = prime_factors.include?(3) ? "Pling" : ""
    result += prime_factors.include?(5) ? "Plang" : ""
    result += prime_factors.include?(7) ? "Plong" : ""
  end

  def self.result_or_int_as_string(result, int)
    result.empty? ? int.to_s : result
  end
end
