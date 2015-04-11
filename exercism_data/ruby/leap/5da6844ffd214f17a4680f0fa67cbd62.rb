class Year

  def self.leap?(year)
    if year.divisible?(100)
      return false unless year.divisible?(400)
    end
    year.divisible?(4)
  end

end

class Integer
  def divisible?(n)
    self % n == 0
  end
end
