class Year
  def self.leap? year
    case 
      when year.divisible_by?(400) then true
      when year.divisible_by?(100) then false
      when year.divisible_by?(4) then true
      else false
    end
  end
end

class Integer
  def divisible_by?(n)
    self % n == 0
  end
end
      
