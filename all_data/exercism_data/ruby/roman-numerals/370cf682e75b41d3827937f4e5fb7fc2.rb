class Fixnum

  def to_roman
    # num.gsub!(/[I]/, '1') unless num.to_i

    romans = {
    'I' => 1,
    'V' => 5,
    'X' => 10,
    'L' => 50,
    'C' => 100,
    'D' => 500,
    'M' => 1000
    }

    num = self
   
    # calculate M's
    ems = num / 1000
    result = 'M' * ems
    num -= 1000 * ems

    # calculate D's
    if num >= 900
      result << 'CM'
      num -= 900
    elsif num >= 500
      result << 'D'
      num -= 500
    end

    # calculate C's
    if num >= 400
      result << 'CD'
      num -= 400
    else
      sees = num / 100
      result << 'C' * sees
      num -= 100 * sees
    end

    # calculate L's
    if num >= 90
      result << 'XC'
      num -= 90
    elsif num >= 50
      result << 'L'
      num -= 50
    end

    # calculate X's
    if num >= 40
      result << 'XL'
      num -= 40
    else
      exes = num / 10
      result << 'X' * exes
      num -= 10 * exes
    end

    # calculate V's
    if num == 9
      result << 'IX'
      num -= 9
    elsif num >= 5
      result << 'V'
      num -= 5
    end

    # calculate I's
    if num == 4
      result << 'IV'
    else
      result << 'I' * num
    end
    
    result
  end
end
