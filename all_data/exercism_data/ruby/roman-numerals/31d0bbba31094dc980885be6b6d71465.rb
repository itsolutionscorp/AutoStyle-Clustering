class Fixnum

  def basic_trans
    case self
      when 1 then 'I'
      when 2 then 'II'
      when 3 then 'III'
      when 4 then 'IV'
      when 5 then 'V'
      when 6 then 'VI'
      when 7 then 'VII'
      when 8 then 'VIII'
      when 9 then 'IX'
      when 10 then 'X'
      when 50 then 'L'
      when 100 then 'C'
      when 500 then 'D'
      when 1000 then 'M'
    end
  end

  def is_basic?
    [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 50, 100, 500, 1000].include?(self)
  end

  
  def to_roman(roman_nums='')

    if self.is_basic?
      roman_nums +=  self.basic_trans    

    elsif (self / 1000) > 0 
      roman_nums += (self % 1000).to_roman 'M'*(self / 1000)

    elsif (self / 500) > 0 && (1000 - self <= 100)
      roman_nums += (self % 100).to_roman 'CM'
    elsif (self / 500) > 0 && (1000 - self <= 500)
      roman_nums += (self % 500).to_roman 'D'
    elsif (self / 500) > 0 
      roman_nums += (self % 500).to_roman 'CM'
    
    elsif (self / 100) > 0 && (500 - self <= 100)
      roman_nums += (self % 100).to_roman 'CD'
    elsif (self / 100) > 0 
      roman_nums += (self % 100).to_roman 'C'*(self / 100)

    elsif (self / 50) > 0  && (100 - self <= 10)
      roman_nums += (self%10).to_roman 'XC'
    elsif (self / 50) > 0 
      roman_nums += (self % 50).to_roman 'L'

    elsif (self / 10) > 0 && (50 - self <= 10)        
      roman_nums += (self % 10).to_roman 'XL'
    elsif (self / 10) > 0
      roman_nums += (self % 10).to_roman 'X'*(self / 10)

    end

    return roman_nums
  end

end
