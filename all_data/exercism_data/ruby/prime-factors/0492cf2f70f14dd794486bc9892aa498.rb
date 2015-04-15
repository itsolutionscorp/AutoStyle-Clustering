class PrimeFactors
  
  def self.for(i)
    factor = 2
    value = []
    until i < factor
      div, mod = i.divmod(factor)
      if mod == 0
       value << factor
       number = div 
    else
      factor += 1 
    end
  end
    value
  end
end
