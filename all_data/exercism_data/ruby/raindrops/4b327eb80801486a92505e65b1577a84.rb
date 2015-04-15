require 'prime'

class Raindrops
  def convert(number)
    result = prime_factors(number).map { |prime| raindrop_speak(prime) }
                                  .join
    result.empty?? number.to_s : result
  end

  private

  def raindrop_speak(prime)
    case prime
    when 3 then 'Pling'
    when 5 then 'Plang'
    when 7 then 'Plong'
    end
  end

  def prime_factors(number)
    number.prime_division.map(&:first)
  end
end
