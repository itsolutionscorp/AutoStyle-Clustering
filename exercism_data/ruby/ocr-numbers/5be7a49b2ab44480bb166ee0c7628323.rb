class OCR
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def convert
    single_numbers.each_with_object("") do |digit, s|
      s << NUMBERS.fetch(digit, "?")
    end
  end

  private

  # split input by lienes and split into chunks of 3
  def parse_text
    text.split("\n").map { |r| r.scan(/.../) }  
  end

  # get and format numbers from parsed text
  def single_numbers
    text, ary = parse_text, []   
    until text.empty?
      top, mid, bot, blank = text.shift(4)
      ary.push(*top.zip(mid, bot).map(&:join)) 
      ary.push(",") unless text.empty?  # add comas when input entered on multiple lines
    end
    ary
  end

  NUMBERS = {
    " _ " +
    "| |" +
    "|_|" => "0",

    "   " +
    "  |" +
    "  |" => "1",

    " _ " +
    " _|" +
    "|_ " => "2",

    " _ " +
    " _|" +
    " _|" => "3",

    "   " +
    "|_|" +
    "  |" => "4",

    " _ " +
    "|_ " +
    " _|" => "5",

    " _ " +
    "|_ " +
    "|_|" => "6",

    " _ " +
    "  |" +
    "  |" => "7",

    " _ " +
    "|_|" +
    "|_|" => "8",

    " _ " +
    "|_|" +
    " _|" => "9",

      "," => ","
  }
end
