class Year
  def self.leap?(year)
    self.is_divisible(year)


  end
end

def is_divisible(number)
  if number % 4 == 0
    true
  end

  def is_not_divisible(number)
    if number % 100 == 0 && number % 400 != 0
      false
    end
  end
end
