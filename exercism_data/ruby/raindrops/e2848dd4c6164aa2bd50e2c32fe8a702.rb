require "mathn"

class Raindrops

  RAINDROPS_NUMBERS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(number)
    result_converted = Prime.prime_division(number).flatten.uniq
    result_converted.select! { |prime| RAINDROPS_NUMBERS.keys.include?(prime) }
    result_converted.map! { |prime| prime = RAINDROPS_NUMBERS[prime] }
    result_converted << "#{number}" if result_converted.length.zero?
    result_converted.join
  end
end
