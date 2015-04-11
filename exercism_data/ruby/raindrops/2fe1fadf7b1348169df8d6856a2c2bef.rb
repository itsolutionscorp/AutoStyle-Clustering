require 'prime'

class Raindrops

  def self.convert(number)
    converter = Converter.new(number)
    if converter.cannot_translate?
      number.to_s
    else
      converter.translate
    end
  end

end

class Converter

  PHRASES = {"3" => "Pling", "5" => "Plang", "7" => "Plong"}


  def initialize(number)
    @number = number
  end

  def prime_factors
    @factors ||= @number.prime_division.flatten - [1]
  end

  def cannot_translate?
    (prime_factors & [3,5,7]).empty?
  end

  def translate
    prime_factors.map {|prime| Converter::PHRASES[prime.to_s]}.join
  end

end
