class Integer < Numeric

  MAX = 3000

  # Array of roman characters, index corresponds to the power of 10.
  ROMAN_CHARS = [
    {
      :unit_char => 'I',
      :five_char => 'V',
    },
    {
      :unit_char => 'X',
      :five_char => 'L',
    },
    {
      :unit_char => 'C',
      :five_char => 'D',
    },
    {
      :unit_char => 'M',
      :five_char => nil,
    },
  ]

  # Wikipedia says: Modern Roman numerals ... are written by expressing each
  # digit separately starting with the left most digit and skipping any
  # digit with a value of zero.
  def to_roman
    arabic = self.to_i

    raise "Number must be less than #{MAX}" if arabic > MAX

    arabic_chars = arabic.to_s.chars

    roman_chars = arabic_chars.each_with_index.map do |char, index|
      pow = (arabic_chars.length - (index + 1))
      case char.to_i
        when 0
          ''
        when 1..3
          Array.new(char.to_i, ROMAN_CHARS[pow][:unit_char]).join
        when 4
          "#{ROMAN_CHARS[pow][:unit_char]}#{ROMAN_CHARS[pow][:five_char]}"
        when 5
          ROMAN_CHARS[pow][:five_char]
        when 6..8
          "#{ROMAN_CHARS[pow][:five_char]}#{Array.new(char.to_i - 5, ROMAN_CHARS[pow][:unit_char]).join}"
        when 9
          "#{ROMAN_CHARS[pow][:unit_char]}#{ROMAN_CHARS[pow + 1][:unit_char]}"
      end
    end

    roman_chars.join

  end

end
