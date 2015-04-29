class OCR
  NUMBERS = {
    " _\n| |\n|_|" => '0',
    "\n  |\n  |"   => '1',
    " _\n _|\n|_"  => '2',
    " _\n _|\n _|" => '3',
    "\n|_|\n  |"   => '4',
    " _\n|_\n _|"  => '5',
    " _\n|_\n|_|"  => '6',
    " _\n  |\n  |" => '7',
    " _\n|_|\n|_|" => '8',
    " _\n|_|\n _|" => '9'
  }

  def initialize(text)
    @text = text
  end

  def convert
    text.split("\n\n").map do |text_row|
      convert_row(text_row)
    end.join(',')
  end

  private

  attr_reader :text

  def convert_row(text_row)
    text_numbers(text_row).each_with_object('') do |text_number, number|
      number << NUMBERS.fetch(text_number) { '?' }
    end
  end

  def text_numbers(text_row)
    text_row.each_line.map do |line|
      line.scan(/.{3}\n?|.*\n/)
    end.transpose.map do |lines|
      lines.flatten.map(&:rstrip).join("\n")
    end
  end
end
