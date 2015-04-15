class OCR
  def initialize(text)
    @text = text
    @zero = <<-NUMBER.chomp
 _ 
| |
|_|
   
    NUMBER
    @one = <<-NUMBER.chomp
   
  |
  |
   
    NUMBER
    @two = <<-NUMBER.chomp
 _ 
 _|
|_ 
   
    NUMBER
    @three = <<-NUMBER.chomp
 _ 
 _|
 _|
   
    NUMBER
    @four = <<-NUMBER.chomp
   
|_|
  |
   
    NUMBER
    @five = <<-NUMBER.chomp
 _ 
|_ 
 _|
   
    NUMBER
    @six = <<-NUMBER.chomp
 _ 
|_ 
|_|
   
    NUMBER
    @seven = <<-NUMBER.chomp
 _ 
  |
  |
   
    NUMBER
    @eight = <<-NUMBER.chomp
 _ 
|_|
|_|
   
    NUMBER
    @nine = <<-NUMBER.chomp
 _ 
|_|
 _|
   
    NUMBER

    @d = Hash.new("?")
    [@zero, @one, @two, @three, @four, @five, @six, @seven, @eight,
     @nine].each_with_index do |n, i|
      @d[n] = i
    end
  end

  DIGIT_HEIGHT = 4
  DIGIT_WIDTH = 3

  def convert
    lines = @text.split("\n")

    res = ""
    start = 0

    0.step(lines.size - 1, DIGIT_HEIGHT) do |start|
      0.step(lines[0].size - 1, DIGIT_WIDTH).each do |i|
        digit = lines[start, DIGIT_HEIGHT].map { |line| line[i, DIGIT_WIDTH] }.join("\n")
        res << @d[digit].to_s
      end
      res << ","
    end
    res.chomp(",")
  end
end
