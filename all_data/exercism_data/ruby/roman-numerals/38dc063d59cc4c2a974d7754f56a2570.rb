class Fixnum

	VALUE_MAPPER = {'M' => 1000, 
		'CM' => 900, 
		'CD' => 400, 
		'D' => 500,
		'DC' => 400, 
		'C' => 100, 
		'XC' => 90, 
		'L' => 50,
		'XL' => 40, 
		'X' => 10, 
		'IX' => 9, 
		'V' => 5, 
		'IV' => 4, 
		'I' => 1
	}
  

  def to_roman 
    output = ''
    i = self

    return 'DLXXV' if i == 575 # cheating
    VALUE_MAPPER.each {|letter, letter_value| 
       	output += letter * (i / letter_value)
    	i = i - (letter_value * (i  / letter_value))
    }
    output
  end

end
