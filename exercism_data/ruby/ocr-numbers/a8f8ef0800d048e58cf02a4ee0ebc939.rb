class OCR
  ROW_SIZE = 4
  COL_SIZE = 3

  def initialize(text)
    @lines = text.split("\n").reject(&:empty?)
  end

  def convert
    digits_in_rows = []
    each_row { |row| digits_in_rows << digits_in_row(row) }

    digits_in_rows.join(',')
  end

  private

  def each_row
    @lines.each_slice(ROW_SIZE) { |row| yield row }
  end

  def each_column(row)
    sliced = row.map { |line| line.chars.each_slice(COL_SIZE).map(&:join) }
    sliced.transpose.each { |column| yield column }
  end

  def digits_in_row(row)
    digits = []
    each_column(row) { |column| digits << (digit(column.join) || '?') }

    digits.join
  end

  def digit(key)
    {
      ' _ | ||_|   ' => '0', '     |  |   ' => '1',
      ' _  _||_    ' => '2', ' _  _| _|   ' => '3',
      '   |_|  |   ' => '4', ' _ |_  _|   ' => '5',
      ' _ |_ |_|   ' => '6', ' _   |  |   ' => '7',
      ' _ |_||_|   ' => '8', ' _ |_| _|   ' => '9'
    }[key]
  end
end
