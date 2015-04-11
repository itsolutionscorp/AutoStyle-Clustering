class Raindrops

  RAINDROPS_NUMBERS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert(number)
    RAINDROPS_NUMBERS.keys.map { |item| item = (number % item).zero? ? RAINDROPS_NUMBERS[item] : nil }.compact.inject(:+) || number.to_s
  end
end
