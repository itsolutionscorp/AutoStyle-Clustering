class Hexadecimal
  HEXADECIMAL = { 'a' => '10', 'b' => '11', 'c' => '12', 'd' => '13', 'e' => '14', 'f' => '15' }

  def initialize hexadecimal
    hexa = permitted_values?(hexadecimal)
    @hexadecimal = change_letter_to_number(hexa.reverse.split(""))
    self
  end

  def to_decimal
    @hexadecimal.map.with_index { |value, i| value.to_i * 16 ** i }.inject(:+)
  end

  private

  def permitted_values? hexa
    hexa.scan(/[g-z]/).empty? ? hexa : '0'
  end

  def change_letter_to_number hexadecimal
    hexadecimal.each_with_object([]) do |value, hexa|
      if HEXADECIMAL[value].nil?
        hexa << value
      else
        hexa << HEXADECIMAL[value]
      end
    end
  end

end
