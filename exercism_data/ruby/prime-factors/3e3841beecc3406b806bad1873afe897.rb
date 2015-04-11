class PrimeFactors

  # Recursive method overflows the stack for the last test :-(
  # def self.for(number, factor=2)
  #   return [] if number < factor
  #   div, mod = number.divmod(factor)
  #   if mod == 0
  #     [factor] + PrimeFactors.for(div, factor)
  #   else
  #     PrimeFactors.for(number, factor+1)
  #   end
  # end

  def self.for(number)
    factor = 2
    return_value = []
    until number < factor
      div, mod = number.divmod(factor)
      if mod == 0
        return_value << factor
        number = div
      else
        factor += 1
      end
    end
    return_value
  end

end
