class OCR
  NUMBERS = {
    " _\n| |\n|_|\n" => '0',
    "\n  |\n  |\n"   => '1',
    " _\n _|\n|_\n"  => '2',
    " _\n _|\n _|\n" => '3',
    "\n|_|\n  |\n"   => '4',
    " _\n|_\n _|\n"  => '5',
    " _\n|_\n|_|\n"  => '6',
    " _\n  |\n  |\n" => '7',
    " _\n|_|\n|_|\n" => '8',
    " _\n|_|\n _|\n" => '9'
  }

  def initialize(text)
    @text = text
  end

  def convert
    text.split(/(?<=\n)\n/).map do |text_row|
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
      line.scan(/(.{3}\n|.{3}|.*\n)/)
    end.transpose.map do |lines|
      lines.flatten.map do |line|
        "#{line.rstrip}\n"
      end.join
    end
  end
end
