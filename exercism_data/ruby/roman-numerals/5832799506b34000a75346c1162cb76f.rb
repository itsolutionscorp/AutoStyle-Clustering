class Integer < Numeric
  NUMERALS = [
      %w[I V X],
      %w[X L C],
      %w[C D M],
      ['M', '', '']
  ]
  def to_roman
    roman = ""
    self.to_s.reverse.chars.to_a.each_with_index do |digit, decimal_place|
      roman = convert(digit, decimal_place) + roman
    end
    roman
  end

  private
    def convert(digit, decimal_place)
      numeral = digit.to_i
      case numeral
        when 1..3
          NUMERALS[decimal_place][0]*numeral
        when 4
          NUMERALS[decimal_place][0] + NUMERALS[decimal_place][1]
        when 5
          NUMERALS[decimal_place][1]
        when 6..8
          NUMERALS[decimal_place][1] + NUMERALS[decimal_place][0]*(numeral - 5)
        when 9
          NUMERALS[decimal_place][0] + NUMERALS[decimal_place][2]
        else
          ""
      end

    end

end
