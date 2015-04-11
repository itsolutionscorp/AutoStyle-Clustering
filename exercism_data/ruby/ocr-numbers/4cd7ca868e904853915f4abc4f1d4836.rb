DIGITS = "
 _     _  _     _  _  _  _  _ 
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|
                              
".sub(/\A\n/, "")

GARBLE = "?"

DIGIT_COUNT = 10
DIGIT_WIDTH = DIGITS.lines.first.length / DIGIT_COUNT
VIRTUAL_LINE_HEIGHT = DIGITS.lines.length

class OCR
  def initialize(text)
    @text = text
  end

  def convert
    virtual_lines.map { |vline| convert_virtual_line(vline) }.join(",")
  end

  private

  def virtual_lines
    @text.lines.each_slice(VIRTUAL_LINE_HEIGHT)
  end

  def convert_virtual_line(vline)
    virtual_chars(vline).map { |vchar| convert_virtual_char(vchar) }.join
  end

  def virtual_chars(vline)
    vline.map { |subline|
      subline.chomp.chars.each_slice(DIGIT_WIDTH).map { |x| x.join }
    }.transpose
  end

  def convert_virtual_char(vchar)
    DIGIT_COUNT.times.find { |n| digit(n) == vchar } || GARBLE
  end

  def digit(n)
    offset = n * DIGIT_WIDTH
    DIGITS.lines.map { |l| l[offset, DIGIT_WIDTH] }
  end
end
