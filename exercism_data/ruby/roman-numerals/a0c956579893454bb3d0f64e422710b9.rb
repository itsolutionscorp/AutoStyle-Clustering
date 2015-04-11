class Fixnum
  def to_roman
    result = ""

    tokens = {
      1000 => 'M',
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
      1 => 'I',
    }

    running_total = self

    while running_total > 0
      tokens.each do |key, value|
        if running_total >= key
          result << value
          running_total = running_total - key
          break
        end
      end
    end

    result
  end
end
