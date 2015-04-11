class Hexadecimal
  HEXADECIMAL = { 'a' => '10', 'b' => '11', 'c' => '12', 'd' => '13', 'e' => '14', 'f' => '15' }

  def initialize string
    @hexadecimal = parse(string)
  end

  def to_decimal
    return 0 if @hexadecimal.nil?
    @hexadecimal.map.with_index { |value, i| value.to_i * 16 ** i }.inject(:+)
  end

  private

  def parse string
    string.chars.reverse.map { |c| normalize c } if permitted_values? string
  end

  def permitted_values? string
    string.scan(/[g-z]/).empty?
  end

  def normalize hexa
    hexa.match(/\d+/) ? hexa.to_i : HEXADECIMAL[hexa]
  end

end
