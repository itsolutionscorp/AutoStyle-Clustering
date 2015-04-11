class OCR
  DIGIT_HEIGHT = 4
  DIGIT_WIDTH = 3
  UNKNOWN = "?"

  def initialize(text)
    @text = text
    @digits = []
    @digits <<  <<-NUMBER.chomp
 _ 
| |
|_|
   
    NUMBER
    @digits << <<-NUMBER.chomp
   
  |
  |
   
    NUMBER
    @digits << <<-NUMBER.chomp
 _ 
 _|
|_ 
   
    NUMBER
    @digits << <<-NUMBER.chomp
 _ 
 _|
 _|
   
    NUMBER
    @digits << <<-NUMBER.chomp
   
|_|
  |
   
    NUMBER
    @digits << <<-NUMBER.chomp
 _ 
|_ 
 _|
   
    NUMBER
    @digits << <<-NUMBER.chomp
 _ 
|_ 
|_|
   
    NUMBER
    @digits << <<-NUMBER.chomp
 _ 
  |
  |
   
    NUMBER
    @digits << <<-NUMBER.chomp
 _ 
|_|
|_|
   
    NUMBER
    @digits << <<-NUMBER.chomp
 _ 
|_|
 _|
   
    NUMBER
  end

  def convert
    lines = @text.split("\n")
    res = ""
    0.step(lines.size - 1, DIGIT_HEIGHT) do |start|
      0.step(lines[0].size - 1, DIGIT_WIDTH).each do |i|
        digit = lines[start, DIGIT_HEIGHT].map { |line| line[i, DIGIT_WIDTH] }
          .join("\n")
        i = @digits.index(digit)
        res << (i ? i.to_s : UNKNOWN)
      end
      res << ","
    end
    res.chomp(",")
  end
end
