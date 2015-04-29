class OCR

  SIDES = {
    right_side: [[1,2],[2,2]],
    left_side: [[1,0],[2,0]],
    each_middle: [[0,1],[1,1],[2,1]],
    top_middle: [[0,1]],
    top_right: [[1,2]],
    bottom_right: [[2,2]],
    bottom_left: [[2,0]],
    top_left: [[1,0]],
    middle_only: [[1,1]]
  }

  attr_reader :rows, :chunks

  def initialize(raw_text)
    @raw_text = raw_text
    create_text_array
  end

  def convert
    answer = ""
    slice_text[0..(rows*chunks-1)].each do |st|
      answer << OCR.new(st).subtext
    end
    answer
  end
  
  def subtext
    return @raw_text if @raw_text == ","
    deconstruct_text(@raw_text)
    parse_text_array
  end

  # private

  def parse_text_array
    possibilities = [0,1,2,3,4,5,6,7,8,9]

    if is_side?(SIDES[:right_side])
      possibilities = possibilities & [0,1,3,4,7,8,9]
    else
      possibilities = possibilities & [2,5,6]
    end

    if is_side?(SIDES[:left_side])
      possibilities = possibilities & [0,6,8]
    else
      possibilities = possibilities & [1,2,3,4,5,7,9]
    end

    if is_side?(SIDES[:each_middle])
      possibilities = possibilities & [2,3,5,6,8,9]
    else
      possibilities = possibilities & [0,1,4,7]
    end

     if is_side?(SIDES[:middle_only])
       possibilities = possibilities & [2,3,4,5,6,8,9]
     else
       possibilities = possibilities & [0,1,7]
     end

    if is_side?(SIDES[:top_left])
      possibilities = possibilities & [0,4,5,6,8,9]
     else
       possibilities = possibilities & [1,2,3,7]
    end

    if is_side?(SIDES[:top_right])
      possibilities = possibilities & [0,1,2,3,4,7,8,9]
    end

    if is_side?(SIDES[:top_middle])
      possibilities = possibilities & [0,2,3,5,6,7,8,9]
    else
      possibilities = possibilities & [1,4]
    end

    possibilities = "?" unless possibilities.size > 0
    possibilities[0].to_s
  end

  def is_side?(side)
    side.each do |x|
      return false if blank?(*x)
    end
    true
  end

  def blank?(x,y)
    @text_array[x][y] == " "
  end

  def slice_text
    parts = []
    @rows = @raw_text.lines.length/4
    @chunks = @raw_text.lines[0].length/3
    sliced_text = Array.new(chunks*(rows+1)){ Array.new }
    p sliced_text
    rows.times do |r|
      @raw_text.lines[(r*4),4].each do |line|
        chunks.times do |i|
          chunk = [i*3,3]
          sliced_text[i+(r*4)] << line[chunk[0],chunk[1]]
        end
      end
    end
    sliced_text.reject{|u| u =~ /^[\w\n]+$/}.each { |section| parts << section.join("\n") }
    if rows > 1
      rows.times do |i|
        parts[(i+1)*3] = ","
      end
    end
    parts
  end

  def deconstruct_text(part)
    answer = @text_array
    part.lines.each_with_index do |line, row|
      line.chars.each_with_index do |char, col|
        answer[row][col] = char
      end
    end
    answer
  end

  def create_text_array
    x = (0..2).to_a
    y = (0..3).to_a
    @text_array = Hash.new { |hash, key| hash[key] = Hash.new}
    x.product(y).each do |x, y|
      @text_array[x][y] = " "
    end
  end
end

text = <<-NUMBER.chomp
    _  _ 
  | _| _|
  ||_  _|
         
    _  _ 
|_||_ |_ 
  | _||_|
         
 _  _  _ 
  ||_||_|
  ||_| _|
         
NUMBER
o = OCR.new(text)

p o.convert
puts o.slice_text
