class Number < Struct.new(:number)
  def prime_factors
    [3, 5, 7].select { |factor| (number % factor).zero? }
  end
end

class Raindrops
  CONVERSION = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(integer)
    number = Number.new(integer)

    if number.prime_factors.any?
      CONVERSION.values_at(*number.prime_factors).join
    else
      integer.to_s
    end
  end
end
