class Year 
  def self.leap?(year)
    if evenly_divisible_by(year, 4)
      if evenly_divisible_by(year,400)
        true
      else
        if evenly_divisible_by(year,100)
          false
        else
          true
        end
      end
    end
  end

  def self.evenly_divisible_by(num, divisor)
    num % divisor == 0
  end
end
