class OCR
  Shapes = [
    " _ | ||_|   ",
    "     |  |   ",
    " _  _||_    ",
    " _  _| _|   ",
    "   |_|  |   ",
    " _ |_  _|   ",
    " _ |_ |_|   ",
    " _   |  |   ",
    " _ |_||_|   ",
    " _ |_| _|   ",
  ]
  def initialize (text)
    @text = text
  end
  def convert
    @text.split("\n").map do |l|
      l.chars.each_slice(3).map(&:join)
    end.each_slice(4).map do |first, *others|
      first.zip(*others).map do |code|
        (Shapes.index(code.join) || "?").to_s
      end.join
    end.join(",")
  end
end
