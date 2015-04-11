class Raindrops < Struct.new(:number)
  def self.convert(number)
    new(number).convert
  end

  def convert
    str = prime_factor_strings.each_with_object("") do |(factor, name), string|
      contains_prime_factor?(factor) && string << name
    end
    (str == "" && number.to_s) || str
  end

protected

  def contains_prime_factor? *factors
    factors.any? { |factor| number % factor == 0 }
  end

  def prime_factor_strings
    {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end
end
