class OCR
  DIGIT_TEMPLATE = [" _     _  _     _  _  _  _  _ ",
                    "| |  | _| _||_||_ |_   ||_||_|",
                    "|_|  ||_  _|  | _||_|  ||_| _|"]

  def initialize text
    @text = text
  end

  def convert
    numbers = @text.split /\n\n/
    numbers.map { |number| convert_number number }.join ','
  end

  def convert_number number
    # Split the number into rows and pad them to equal length.
    rows = number.split("\n")[0,3]
    rows = rows.map { |row| row.ljust rows.map(&:length).max }

    # Section the rows into digits and convert digit-by-digit.
    digits = rows.map { |row| row.scan(/.{3}/) }.transpose
    digits.map { |digit| convert_digit digit }.join
  end

  def convert_digit digit_rows
    (0..9).each do |number|
      return number.to_s if digit_matches digit_rows, number
    end
    '?'
  end

  def digit_matches digit_rows, number
    digit_rows.zip(DIGIT_TEMPLATE).all? do |row, template|
      row == template[3*number, 3]
    end
  end
end
