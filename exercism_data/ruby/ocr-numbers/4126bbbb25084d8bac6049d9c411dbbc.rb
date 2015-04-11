class OCR
  NUMBER_HEIGHT = 4
  NUMBERS = {
    " _ \n" \
    "| |\n" \
    "|_|\n" \
    "   " => "0",

    "   \n" \
    "  |\n" \
    "  |\n" \
    "   "   => "1",

    " _ \n" \
    " _|\n" \
    "|_ \n" \
    "   " => "2",

    " _ \n" \
    " _|\n" \
    " _|\n" \
    "   " => "3",

    "   \n" \
    "|_|\n" \
    "  |\n" \
    "   "   => "4",

    " _ \n" \
    "|_ \n" \
    " _|\n" \
    "   "  => "5",

    " _ \n" \
    "|_ \n" \
    "|_|\n" \
    "   "  => "6",

    " _ \n" \
    "  |\n" \
    "  |\n" \
    "   " => "7",

    " _ \n" \
    "|_|\n" \
    "|_|\n" \
    "   " => "8",

    " _ \n" \
    "|_|\n" \
    " _|\n" \
    "   " => "9"
  }

  def initialize(text)
    @text = text
  end

  def convert
    rows.each.with_object("") { |row, result|
      numbers = numbers_in_row(row)
      numbers.each do |number|
        result << look_up_value(number)
      end
      result << ","
    }.chop
  end

  private

  def look_up_value(number)
    value = NUMBERS[number]
    value ? value : "?"
  end

  def rows
    @text.split(/\n/).each_slice(NUMBER_HEIGHT).with_object([]) do |lines, rows|
      rows << lines.join("\n")
    end
  end

  def numbers_in_row(row)
    row.split("\n").map { |line|
      line.scan(/.{3}/)
    }.transpose.map { |lines| lines.join("\n") }
  end
end
