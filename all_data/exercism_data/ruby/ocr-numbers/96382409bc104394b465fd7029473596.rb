class OCR
  def initialize(text)
    @text = text 
  end

  def convert
    @text.lines.each_slice(4).map { |line| convert_line(line) }.join ","
  end

private
  def convert_line(line)
    self.class.digits(line).map { |digit| NUMBERS[digit] }.join
  end

  def self.digits(text)
    text.each_with_object([]) do |line, result|
      line.scan(/.{3}/).each_with_index do |part, index|
        result[index] ||= []
        result[index] << part
      end
    end
  end

  numbers = <<-END.chomp
 _     _  _     _  _  _  _  _ 
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|
                              
  END

  NUMBERS = Hash.new("?").tap do |hash|
    digits(numbers.lines).map.with_index { |code, index| hash[code] = index }
  end

end
