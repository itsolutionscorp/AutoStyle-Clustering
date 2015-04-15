class Raindrops
  def self.convert(input)
    @number = input
    output = ''
    output << "Pling"     if divisible_by?(3)
    output << "Plang"     if divisible_by?(5)
    output << "Plong"     if divisible_by?(7)
    output << input.to_s  if output.empty?
    output
  end
  def self.divisible_by?(divisor)
    @number % divisor == 0
  end
end
