class OCR
  def initialize(text)
    @text = text
  end

  def convert
    @text.scan(number_regex).map { |number_string|
      convert_number number_string
    }.join(",")
  end

  private
  def number_regex
    /[ _|]+\n[ _|]+\n[ _|]+\n[ ]+\n?/
  end

  def convert_number(number_string)
    @digits = []
    number_string.split("\n").each { |line| get_segments_from line }
    @digits.map { |d| convert_digit d.join("\n") }.join
  end

  def get_segments_from(line)
    col = 0
    line.scan(/.{3}/).map { |segment_string|
      @digits[col] ||= []
      @digits[col] << segment_string
      col += 1
    }
  end

  def convert_digit(digit_string)
    DIGIT_STRINGS.each_with_index { |d, i| return i if digit_string == d }
    return "?"
  end

    DIGIT_STRINGS = [
" _ 
| |
|_|
   ",
"   
  |
  |
   ",
" _ 
 _|
|_ 
   ",
" _ 
 _|
 _|
   ",
"   
|_|
  |
   ",
" _ 
|_ 
 _|
   ",
" _ 
|_ 
|_|
   ",
" _ 
  |
  |
   ",
" _ 
|_|
|_|
   ",
" _ 
|_|
 _|
   "
]
end
