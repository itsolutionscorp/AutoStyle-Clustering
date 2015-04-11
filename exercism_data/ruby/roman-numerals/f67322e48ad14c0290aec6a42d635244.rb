class Integer

  UNIT      = {minor: 'I', middle: 'V', major: 'X'}
  DOZENS    = {minor: 'X', middle: 'L', major: 'C'}
  HUNDREDS  = {minor: 'C', middle: 'D', major: 'M'}
  THOUSANDS = 'M'

  def to_roman
    numbers  = self.to_s.chars
    as_roman = []
    while numbers.size > 0
      as_roman << convert_base(numbers)
      numbers.shift
    end
    as_roman.join
  end

  def convert_base(number)
    return THOUSANDS * number.first.to_i if number.size == 4

    order = case number.size
              when 1 then UNIT
              when 2 then DOZENS
              when 3 then HUNDREDS
            end

    if number.first.to_i < 4
      order[:minor] * number.first.to_i
    elsif number.first.to_i == 4
      order[:minor] + order[:middle]
    elsif number.first.to_i == 5
      order[:middle]
    elsif number.first.to_i < 9
      order[:middle] + order[:minor] * (number.first.to_i - 5)
    else
      order[:minor] + order[:major]
    end
  end
end
