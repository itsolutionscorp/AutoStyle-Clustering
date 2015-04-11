class Array
  def safe_transpose
    result = []
    max_size = self.max { |a,b| a.size <=> b.size }.size
    max_size.times do |i|
      result[i] = Array.new(self.first.size)
      self.each_with_index { |r,j| result[i][j] = r[i] }
    end
    result
  end
end

class OCR

  NUMBERS = {
    :" _\n| |\n|_|\n" => "0",
    :" _\n| |\n|_|\n" => "0",
    :"\n  |\n  |\n"   => "1",
    :" _\n _|\n|_\n"  => "2",
    :" _\n _|\n _|\n" => "3",
    :"\n|_|\n  |\n"   => "4",
    :" _\n|_\n _|\n"  => "5",
    :" _\n|_\n|_|\n"  => "6",
    :" _\n  |\n  |\n" => "7",
    :" _\n|_|\n|_|\n" => "8",
    :" _\n|_|\n _|\n" => "9"
  }
  NUMBERS.default = "?"

  FULL_NUMBERS = {
    :"   " => ",",
    :" _ \n| |\n|_|\n" => "0",
    :"   \n  |\n  |\n" => "1",
    :" _ \n _|\n|_ \n" => "2",
    :" _ \n _|\n _|\n" => "3",
    :"   \n|_|\n  |\n" => "4",
    :" _ \n|_ \n _|\n" => "5",
    :" _ \n|_ \n|_|\n" => "6",
    :" _ \n  |\n  |\n" => "7",
    :" _ \n|_|\n|_|\n" => "8",
    :" _ \n|_|\n _|\n" => "9"
  }
  FULL_NUMBERS.default = "?"  

  MAX_NUMBER_LENGTH = NUMBERS.keys.max.length

  attr_reader :text

  def initialize(text)
    @text =  text
  end

  def convert
    one_number? ? convert_one_number : convert_multiple_numbers
  end

  private
  def convert_one_number
    NUMBERS[text.to_sym]
  end

  def convert_multiple_numbers
    digits.map do |digit|
      NUMBERS[digit.to_sym] == NUMBERS.default ? FULL_NUMBERS[digit.to_sym] : NUMBERS[digit.to_sym]
    end.join
  end

  def lines
    text.split("\n")
  end

  def three_characters
    lines.map do |line|
      line.scan(/...|../)
    end
  end

  def digits
    three_characters.safe_transpose.map do |character|
      character.compact.join("\n") << "\n"
    end
  end

  def one_number?
    text.length <= MAX_NUMBER_LENGTH
  end

end
