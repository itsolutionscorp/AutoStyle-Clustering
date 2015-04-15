class Fixnum
  def divisible_by?(number)
    self % number == 0
  end
end

class Raindrops
  def self.convert(number)
    raindrops = ""
    raindrops << 'Pling' if number.divisible_by?(3)
    raindrops << 'Plang' if number.divisible_by?(5)
    raindrops << 'Plong' if number.divisible_by?(7)

    if raindrops.empty?
      number.to_s
    else
      raindrops
    end
  end
end
