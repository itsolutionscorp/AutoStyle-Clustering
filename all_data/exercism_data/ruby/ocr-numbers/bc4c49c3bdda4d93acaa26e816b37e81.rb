class OCR
  def self.split_numbers line
    line.chomp.lines
      .map{|row| row.chomp.scan /.{3}/}
      .transpose
      .map{|digit|digit.join "\n"}
  end
  
  DIGIT_FIELD = <<-NUMBER.chomp
 _     _  _     _  _  _  _  _ 
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|
                              
  NUMBER
  DIGITS = split_numbers DIGIT_FIELD
  
  def initialize text
    @lines = split_lines text
  end
  
  def convert
    @value ||= lines.map{|line| convert_line line }.join(',')
  end
  
  private
  attr_reader :lines
  def convert_line line
    split_numbers(line).each_with_object("") do |number, output|
      output << convert_digit(number)
    end
  end
  
  def split_numbers line
    OCR.split_numbers(line)
  end
  
  def convert_digit number
    (DIGITS.find_index(number) || "?").to_s
  end
  
  def split_lines text
    "#{text}\n".scan(/(?<num>(.*\n){4})/).flatten
  end
end
