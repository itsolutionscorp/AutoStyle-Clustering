module Raindrops
  extend self

  def convert(num)
    factors_of_num = factors(num)
    translate(factors_of_num, num)
  end

  def replacements
    {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
  end

  def translate(factors, num)
    translation = factors.uniq.map do |factor|
      if replacements.has_key?(factor)
        replacements[factor] 
      end
    end.join
    translation.empty? ? num.to_s : translation
  end

  def factors(num)
    factors = []
    i = 2
    while num>1 && num != i
      while num % i == 0
        factors << i 
        num = num / i
      end
      i += 1
    end
    factors << num
    factors
  end

end
