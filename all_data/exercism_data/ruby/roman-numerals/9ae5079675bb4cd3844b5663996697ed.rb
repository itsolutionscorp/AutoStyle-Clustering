# nooooooooo
class Fixnum

  # lookahead numerals are really just subcases of double-wide numbers, amirite?
  ROMAN_NUMERALS = [[1000,'M'],[900,'CM'],[500,'D'],[400,'CD'],
                    [100,'C'],[90, 'XC'],[50,'L'],[40,'XL'],[10,'X'],
                    [9,'IX'],[5,'V'],[4,'IV'],[1,'I']
                   ]

  def to_roman
    number = self
    ROMAN_NUMERALS.inject('') do |roman,(value,char)|
      roman += char * (number / value)
      number = number % value
      roman
    end
  end

end
