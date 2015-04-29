# pure functions
class Raindrops
  def self.convert(number)
    non_divisible(number,
      plong(number,
        plang(number,
          pling(number, ""))))
  end

  { pling: 3, plang: 5, plong: 7 }.each_pair do |symbol, factor|
    Raindrops.define_singleton_method(symbol) do |number, word|
      sound(number, word, factor, symbol.to_s.capitalize)
    end
  end

  def self.sound(number, word, factor, sound)
    divisible_by?(number, factor) ? word + sound : word
  end

  def self.divisible_by?(number, divisor)
    (number % divisor).zero?
  end

  def self.non_divisible(number, string)
    string.empty? ? number.to_s : string
  end
end
