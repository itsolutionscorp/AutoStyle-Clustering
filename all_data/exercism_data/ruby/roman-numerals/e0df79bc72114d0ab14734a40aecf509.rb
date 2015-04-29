class Integer

  def to_roman
    number = self
    romans = { 1 => 'I', 5 => 'V', 10 => 'X', 50 => 'L', 100 => 'C', 500 => 'D', 1000 => 'M'}
    result = ''

    while number > 0
      if number >= 900 && number < 1000
        result << romans[100] + romans[1000] && number -= 900
      elsif number >= 400 && number < 500
        result << romans[100] + romans[500] && number -= 400
      elsif number >= 90 && number < 100
        result << romans[10] + romans[100] && number -= 90
      elsif number >= 40 && number < 50
        result << romans[10] + romans[50] && number -=40
      elsif number == 9
        result << romans[1] + romans[10] && number -= 9
      elsif number == 4
        result << romans[1] + romans[5] && number -= 5
      elsif number >= 1000
        result << romans[1000] && number -= 1000
      elsif number >= 500
        result << romans[500] && number -= 500
      elsif number >= 100
        result << romans[100] && number -= 100
      elsif number >= 50
        result << romans[50] && number -= 50
      elsif number >= 10
        result << romans[10] && number -= 10
      elsif number >= 5
        result << romans[5] && number -= 5
      else
        result << romans[1] && number -= 1
      end
    end
    result
  end

end
