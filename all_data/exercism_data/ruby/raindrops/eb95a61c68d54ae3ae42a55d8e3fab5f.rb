module Raindrops

  extend self

  require 'prime'

  def convert(num)
    result = ""
    factorization = Prime.prime_division(num).flatten

    if factorization.include?(3)
      result += "Pling"
    end

    if factorization.include?(5)
      result += "Plang"
    end

    if factorization.include?(7)
      result += "Plong"
    end

    if result.empty?
      result = num.to_s
    end

    result

  end

end
