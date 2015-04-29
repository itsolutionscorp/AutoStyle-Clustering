class OCR
  def initialize(text)
    @text = text
  end


  NUMERALS = {
    " _ \n| |\n|_|\n   " => '0',
    "   \n  |\n  |\n   " => '1',
    " _ \n _|\n|_ \n   " => '2',
    " _ \n _|\n _|\n   " => '3',
    "   \n|_|\n  |\n   " => '4',
    " _ \n|_ \n _|\n   " => '5',
    " _ \n|_ \n|_|\n   " => '6',
    " _ \n  |\n  |\n   " => '7',
    " _ \n|_|\n|_|\n   " => '8',
    " _ \n|_|\n _|\n   " => '9'
  }

  def convert
    blocks.map do |chars|
      chars.map {|c| NUMERALS.fetch(c, "?") }.join
    end.join(",")
  end

  private

  attr_reader :text

  def lines
    @lines ||= text.lines.each_slice(4).map(&:join)
  end


  def blocks
    @blocks ||= lines.map {|line| MatrixToBlock.new(line).to_a }
  end

  # Takes in a four-line string of digital numbers and splits them into 3x4 
  # single-character blocks.
  #
  class MatrixToBlock
    def initialize(text)
      @text = text
    end

    def to_a
      transposed.map {|x| x.map(&:join).join("\n") }
    end

    private

    attr_reader :text

    def transposed
      char_lines.map {|x| x.each_slice(3).to_a }.transpose
    end

    def char_lines
      text.split("\n").map {|l| l.each_char.to_a }
    end
  end
end
