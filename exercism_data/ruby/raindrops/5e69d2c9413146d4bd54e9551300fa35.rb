class Raindrops

  def convert num
    unless prime_factor?(num)
      return num.to_s
    end

    result = ''
    result << 'Pling' if div_by?(3, num)
    result << 'Plang' if div_by?(5, num)
    result << 'Plong' if div_by?(7, num)
    result
  end

  def prime_factor?(num)
    (div_by?(3, num) || div_by?(5, num) || div_by?(7 ,num)) 
  end

  def div_by?(divisor, num)
    num % divisor == 0    
  end

end
