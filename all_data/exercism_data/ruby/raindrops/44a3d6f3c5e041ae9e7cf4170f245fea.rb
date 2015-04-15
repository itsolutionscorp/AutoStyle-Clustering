class Raindrops

  def convert num
    result = ''
    result << 'Pling' if divisible_by?(3, num)
    result << 'Plang' if divisible_by?(5, num)
    result << 'Plong' if divisible_by?(7, num)
    result == '' ? num.to_s : result
  end

  def divisible_by?(divisor, num)
    num % divisor == 0    
  end

end
