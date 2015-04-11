class Integer
  BASE = [1, 5, 10, 50, 100, 500, 1000]
  ROMANS = ['I', 'V', 'X', 'L', 'C', 'D', 'M']

  def to_roman
    coefficients = coefficients(self)
    units = romanize(coefficients[0..1], ROMANS[0..2])
    dizains = romanize(coefficients[2..3], ROMANS[2..4])
    hundreds = romanize(coefficients[4..5], ROMANS[4..6])
    thousands = ROMANS[6]*coefficients[6]
    
    thousands + hundreds + dizains + units
  end
  
  private 
  
  def romanize coefficients, romans
    if coefficients[0] <= 3
      romans[1]*coefficients[1] + romans[0]*coefficients[0]
    elsif coefficients[1] == 0
      romans[0] + romans[1]
    else
      romans[0] + romans[2]
    end
  end
  
  def coefficients n #Decompostion of n : n = a*1 + b*5 + c*10 + d*50 + e*500 + f*1000
    BASE.reverse.inject([]) { |coefs, base| q, n = n.divmod(base); coefs.insert(0, q) }
  end
  
end
