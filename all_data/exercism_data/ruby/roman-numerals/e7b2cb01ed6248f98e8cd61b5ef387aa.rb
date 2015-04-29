class Fixnum

  @@mappings = {
    thousands: { nine: '', four: '', five: '', one: 'M' },
    hundreds:  { nine: 'CM', four: 'CD', five: 'D', one: 'C' },
    tens:      { nine: 'XC', four: 'XL', five: 'L', one: 'X' },
    ones:      { nine: 'IX', four: 'IV', five: 'V', one: 'I' }
  }

  def to_roman
    numeral = ''

    thousands = self / 1000
    hundreds  = self % 1000 / 100
    tens      = self % 100 / 10
    ones      = self % 10

    numeral += convert_place_value(:thousands, thousands)
    numeral += convert_place_value(:hundreds, hundreds)
    numeral += convert_place_value(:tens, tens)
    numeral += convert_place_value(:ones, ones)

    numeral
  end

  def convert_place_value(column, value)
    output = ''
    case value
    when 9
      output += @@mappings[column][:nine]
    when 4
      output += @@mappings[column][:four]
    when value
      output += @@mappings[column][:five] * (value / 5)
      output += @@mappings[column][:one] * (value % 5)
    end
    output
  end
end
