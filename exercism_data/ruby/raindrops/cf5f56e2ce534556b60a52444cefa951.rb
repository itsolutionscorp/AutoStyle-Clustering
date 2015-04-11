module Raindrops
  extend self

  def convert(number)
    result = ""
    result += "Pling" if prime_factor?(number, 3)
    result += "Plang" if prime_factor?(number, 5)
    result += "Plong" if prime_factor?(number, 7)

    result.empty? ? number.to_s : result
  end

private

  def prime_factor?(number, factor)
    number % factor == 0
  end
end
