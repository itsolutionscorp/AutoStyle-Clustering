class Integer
  def to_roman
    current_value = self
    roman = ''    

    while current_value > 0 do
      largest_roman_fragment_that_fits = 
        ROMAN_NUMBER_FRAGMENTS
          .select{|f| f.value <= current_value}
          .first

      roman += largest_roman_fragment_that_fits.roman_str
      current_value -= largest_roman_fragment_that_fits.value
    end

    roman
  end

  private 

  RomanFragment = Struct.new :value, :roman_str

  ROMAN_NUMBER_FRAGMENTS = [
    RomanFragment.new(1000, 'M' ),
    RomanFragment.new( 900, 'CM'),
    RomanFragment.new( 500, 'D' ),
    RomanFragment.new( 400, 'CD'),
    RomanFragment.new( 100, 'C' ),
    RomanFragment.new(  90, 'XC'),
    RomanFragment.new(  50, 'L' ),
    RomanFragment.new(  40, 'XL'),
    RomanFragment.new(  10, 'X' ),
    RomanFragment.new(   9, 'IX'),
    RomanFragment.new(   5, 'V' ),
    RomanFragment.new(   4, 'IV'),
    RomanFragment.new(   1, 'I' ),
  ]
end
