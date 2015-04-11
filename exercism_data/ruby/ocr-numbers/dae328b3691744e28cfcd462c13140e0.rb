class OCR
  # A digit's hash is simply its 3 rows joined together.
  HASHES = { ' _ | ||_|' => 0, '     |  |' => 1, ' _  _||_ ' => 2, 
             ' _  _| _|' => 3, '   |_|  |' => 4, ' _ |_  _|' => 5,
             ' _ |_ |_|' => 6, ' _   |  |' => 7, ' _ |_||_|' => 8,
             ' _ |_| _|' => 9 }

  def initialize text
    @text = text
  end

  def convert
    # Split into 3 row numbers, and convert number-by-number.
    numbers = @text.split /\n\n/
    numbers.map { |number| convert_number number }.join ','
  end

  def convert_number number
    # Split the number into rows and pad them to equal length.
    rows = number.split("\n")
    rows = rows.map { |row| row.ljust rows.map(&:length).max }

    # Section the rows into 3-long strings, and join strings
    # from the same digits to make hashes.
    sectioned_rows = rows.map { |row| row.scan(/.{3}/) }
    digit_hashes = sectioned_rows.transpose.map &:join
    digit_hashes.map { |hash| HASHES[hash] || '?' }.join
  end
end
