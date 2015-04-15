class Integer
  BASE = [1, 5, 10, 50, 100, 500, 1000]
  ROMANS = ['I', 'V', 'X', 'L', 'C', 'D', 'M']

  def to_roman
    coefficients = coefficients(self)
    (0..6).step(2).inject('') { |roman, n|
      romanize(coefficients[n..n+1], ROMANS[n..n+2]) + roman
    }
  end
  
  private 
  
  def romanize coefficients, romans
    if romans[1].nil? # Last unit 'M'
      romans[0]*coefficients[0]
    elsif coefficients[0] <= 3
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
