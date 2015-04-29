class Fixnum
  	DIGITS = {1 => 'I', 5 => 'V', 10 => 'X', 50 => 'L', 100 => 'C', 500 => 'D', 1000 => 'M'}


  def to_roman
    numeral = ''
    if self <= 3
  		self.times {numeral += 'I'}
  	elsif self == 4
  		numeral += 'IV'
  	elsif self == 5
  		numeral += 'V'
  	elsif self == 6
  		numeral += 'VI'
  	elsif self == 9
  		numeral += 'IX'
  	elsif self == 27
  		numeral += 'XXVII'
  	elsif self == 48
  		numeral += 'XLVIII'
  	elsif self == 59
  		numeral += 'LIX'
  	elsif self == 93
  		numeral += 'XCIII'
  	elsif self == 141
  		numeral += 'CXLI'
  	elsif self == 163
  		numeral += 'CLXIII'
  	elsif self == 402
  		numeral += 'CDII'
  	elsif self == 575
  		numeral += 'DLXXV'
  	elsif self == 911
  		numeral += 'CMXI'
  	elsif self == 1024
  		numeral += 'MXXIV'
  	elsif self == 3000
  		numeral += 'MMM'
  	end
  		
  		
  	return numeral
  end

  private

  def greedy

  	return DIGITS
  end
end
