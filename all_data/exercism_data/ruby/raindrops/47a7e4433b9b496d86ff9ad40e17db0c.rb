class Raindrops
  def self.convert(num)
    str = strings(factors(num))
    str.length.zero? ? num.to_s : str
  end

  def self.factors(num)
    [3,5,7].select { |n| (num % n).zero? }
  end

  def self.strings(factor)
    "#{'Pling' if factor.include?(3)}#{'Plang' if factor.include?(5)}#{'Plong' if factor.include?(7)}"
  end
end
