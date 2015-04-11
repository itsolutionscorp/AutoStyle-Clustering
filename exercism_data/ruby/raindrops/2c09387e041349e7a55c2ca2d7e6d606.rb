class Raindrops
  DICTIONARY = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

  def self.convert(number)
    new(number).convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    return @number.to_s if dividers.length == 0
    dividers.map {|i| DICTIONARY[i]}.inject(:+)
  end

  private

  def dividers
    factors.select {|x| divisible_by x}
  end

  def factors
    DICTIONARY.keys
  end

  def divisible_by(divider)
    @number % divider == 0
  end
end
