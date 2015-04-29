# rubocop:disable Documentation
class OCR
  def initialize(text)
    @text = text
  end

  def convert
    read_block(@text)
  end

  private

  FONT = <<-FONT
 _     _  _     _  _  _  _  _
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|
FONT

  DIGIT_WIDTH = 3
  DIGIT_HEIGHT = 4

  def read_block(block_text)
    OCR.split_rows(block_text)
      .map { |r| read_row r }.join ','
  end

  def read_row(row_text)
    OCR.split_digits(row_text)
      .map { |d| recognize_digit d }
      .join
  end

  def recognize_digit(text)
    idx = FONT_DIGITS.index(text.rstrip) || '?'
    idx.to_s
  end

  def self.split_rows(block_text)
    block_text.lines
      .each_slice(DIGIT_HEIGHT)
      .map(&:join)
  end

  def self.split_digits(row_text)
    count = row_text.lines.map(&:length).max / DIGIT_WIDTH

    [].tap do |output|
      count.times do |i|
        output << get_ith_digit_text(row_text, i)
      end
    end
  end

  def self.get_ith_digit_text(row_text, i)
    row_text.lines
      .map { |l| l.chomp.slice(i * DIGIT_WIDTH, DIGIT_WIDTH) }
      .map { |l| (l || '').ljust(DIGIT_WIDTH) }
      .join("\n")
      .rstrip
  end

  FONT_DIGITS = split_digits(FONT)
end
