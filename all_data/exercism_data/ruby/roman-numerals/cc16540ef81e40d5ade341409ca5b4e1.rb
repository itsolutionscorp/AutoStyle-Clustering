class Fixnum
  def to_roman
    number = self   
    roman = ''
    NUMBER_TO_ROMAN.each do |test, character|
      if number >= test
        division = number.divmod(test)
        roman << character*division[0]
        number = division[1]
      end
    end
    roman
  end
  
  private
  
  NUMBER_TO_ROMAN = { 1000 => 'M',
                       900 => 'CM',
                       500 => 'D',
                       400 => 'CD',
                       100 => 'C',
                        90 => 'XC',
                        50 => 'L',
                        40 => 'XL',  
                        10 => 'X', 
                         9 => 'IX', 
                         5 => 'V', 
                         4 => 'IV', 
                         1 => 'I'}
end
