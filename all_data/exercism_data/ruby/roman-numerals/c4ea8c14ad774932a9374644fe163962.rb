class Integer
  RN3 = {
	9 => 'CM',
	5 => 'D',
	4 => 'CD',
	1 => 'C',
  }
  RN2 = {
	9 => 'XC',
	5 => 'L',
	4 => 'XL',
	1 => 'X',
  }
  RN1 = {
	9 => 'IX',
	5 => 'V',
	4 => 'IV',
	1 => 'I',
  }

  def to_roman
	numIn = self
	numStr = format("%04d", self.to_s)
	roman = ''
	
	roman << 'M'*(numStr[0].to_i)

	digit3 = numStr[1].to_i
	case digit3
	when 4, 5, 9
	  roman << RN3[digit3]
	when 6..8
	  roman << RN3[5]
	  roman << RN3[1]*(digit3%5)
	when 1..3
	  roman << RN3[1]*digit3
	end
	
	digit2 = numStr[2].to_i
	case digit2
	when 4, 5, 9
	  roman << RN2[digit2]
	when 6..8
	  roman << RN2[5]
	  roman << RN2[1]*(digit2%5)
	when 1..3
	  roman << RN2[1]*digit2
	end
	
	digit1 = numStr[3].to_i
	case digit1
	when 4, 5, 9
	  roman << RN1[digit1]
	when 6..8
	  roman << RN1[5]
	  roman << RN1[1]*(digit1%5)
	when 1..3
	  roman << RN1[1]*digit1
	end

	roman

  end
end
