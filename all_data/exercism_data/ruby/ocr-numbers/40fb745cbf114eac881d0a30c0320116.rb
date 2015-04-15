class OCR
  def initialize(text)
    @text = text 
  end

  def convert
    text_lines.map { |line| convert_line(line) }.join ","
  end

private
  def text_lines
    @text.split("\n").each_slice(4)
  end

  def convert_line(line)
    self.class.digits(line).map { |digit| NUMBERS[digit] }.join
  end

  def self.digits(rows)
    rows.each_with_object([]) do |row, result|
      row.chars.each_slice(3).with_index do |digit_row, index|
        (result[index] ||= []) << digit_row
      end
    end
  end

  numbers = <<-END.chomp
 _     _  _     _  _  _  _  _ 
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|
                              
  END

  NUMBERS = Hash.new("?").tap do |hash|
    digits(numbers.split "\n").map.with_index { |code, index| hash[code] = index }
  end

end
