class Integer
  RN3 = {
	900 => 'CM',
	500 => 'D',
	400 => 'CD',
	100 => 'C',
  }
  RN2 = {
	90 => 'XC',
	50 => 'L',
	40 => 'XL',
	10 => 'X',
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

	digit3 = (numStr[1].to_i)*100
	case digit3
	when 400, 500, 900
	  roman << RN3[digit3]
	when 600..800
	  roman << RN3[500]
	  roman << RN3[100]*((digit3%500)/100)
	when 100..300
	  roman << RN3[100]*(digit3/100)
	end
	
	digit2 = (numStr[2].to_i)*10
	case digit2
	when 40, 50, 90
	  roman << RN2[digit2]
	when 60..80
	  roman << RN2[50]
	  roman << RN2[10]*((digit2%50)/10)
	when 10..30
	  roman << RN2[10]*(digit2/10)
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
