class Fixnum
  ROMAN_KEY = { 1 => 'I',
                2 => 'II',
			    3 => 'III',
			    4 => 'IV',
			    5 => 'V',
			    6 => 'VI',
			    7 => 'VII',
			    8 => 'VIII',
			    9 => 'IX',
			   10 => 'X',
			   20 => 'XX',
			   30 => 'XXX',
			   40 => 'XL',
			   50 => 'L',
			   60 => 'LX',
			   70 => 'LXX',
			   80 => 'LXXX',
			   90 => 'XC',
			  100 => 'C',
			  200 => 'CC',
			  300 => 'CCC',
			  400 => 'CD',
			  500 => 'D',
			  600 => 'DX',
			  700 => 'DXX',
			  800 => 'DXXX',
			  900 => 'CM',
		     1000 => 'M',
		     2000 => 'MM',
		     3000 => 'MMM' }

  def to_roman
    components = Array.new
    length = self.to_s.length
    
    length.times do |iteration|
      components << self.to_s.chars[iteration] + "0" * (length - (iteration + 1))
    end
    
    ROMAN_KEY.each { |arabic, roman| components.map! { |part| part.sub(/^#{arabic.to_s}$/, roman) } }
    
	return components.join.delete "0"
  end
end
