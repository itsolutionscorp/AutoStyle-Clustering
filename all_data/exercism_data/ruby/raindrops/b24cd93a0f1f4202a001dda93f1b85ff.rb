class Raindrops
  def self.convert(num)

  {3 => "Pling", 5 => "Plang", 7 => "Plong"}.map  do |divisor, word|
    word if num % divisor == 0

  end.compact.tap {|whatever| whatever << num if whatever.empty?}.join


  end
end
