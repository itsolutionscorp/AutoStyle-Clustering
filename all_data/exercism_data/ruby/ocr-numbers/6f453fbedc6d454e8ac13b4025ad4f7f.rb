class OCR
  NUMBERS = {
    :" _\n| |\n|_|" => '0',
    :"\n  |\n  |"   => '1',
    :" _\n _|\n|_"  => '2',
    :" _\n _|\n _|" => '3',
    :"\n|_|\n  |"   => '4',
    :" _\n|_\n _|"  => '5',
    :" _\n|_\n|_|"  => '6',
    :" _\n  |\n  |" => '7',
    :" _\n|_|\n|_|" => '8',
    :" _\n|_|\n _|" => '9'
  }
  NUMBERS.default = "?"

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def convert
    rows.map do |row| 
      convert_row(row) 
    end.join(',')
  end

  private
  def rows
    text.split("\n\n")
  end

  def convert_row(row)
    text_numbers(row).each_with_object('') do |number, output_number|
      output_number << NUMBERS[number.to_sym]
    end
  end

  def text_numbers(row)
    row.each_line.map do |line|
      line.scan(/(.{3}\n|.{3}|.*\n)/)
    end.transpose.map do |lines|
      lines.flatten.map(&:rstrip).join("\n")
    end
  end

end
