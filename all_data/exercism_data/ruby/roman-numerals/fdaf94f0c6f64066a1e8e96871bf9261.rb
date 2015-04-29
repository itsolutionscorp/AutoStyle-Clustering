class Integer

  def to_roman
    number = self
    romans = { 1 => 'I',
      4 => 'IV',
      5 => 'V',
      9 => 'IX',
      10 => 'X',
      40 => 'XL',
      50 => 'L',
      90 => 'XC',
      100 => 'C',
      400 => 'CD',
      500 => 'D',
      900 => 'CM',
      1000 => 'M'
    }
    result = ''

    while number > 0
      if number >= 1000
        result << romans[1000] && number -= 1000
      elsif number >= 900
        result << romans[900] && number -= 900
      elsif number >= 500
        result << romans[500] && number -= 500
      elsif number >= 400
        result << romans[400] && number -= 400
      elsif number >= 100
        result << romans[100] && number -= 100
      elsif number >= 90
        result << romans[90] && number -= 90
      elsif number >= 50
        result << romans[50] && number -= 50
      elsif number >= 40
        result << romans[40] && number -=40
      elsif number >= 10
        result << romans[10] && number -= 10
      elsif number >= 9
        result << romans[9] && number -= 9
      elsif number >= 5
        result << romans[5] && number -= 5
      elsif number >= 4
        result << romans[4] && number -= 4
      else
        result << romans[1] && number -= 1
      end
    end
    result
  end

end
