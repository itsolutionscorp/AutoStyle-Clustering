class OCR
  def initialize(text)
    @rows = parse(text)
  end

  def convert
    @rows.each_slice(4).map do |line|
      ocr(line)
    end.join(',')
  end

  def parse(str)
    str.split("\n")
  end

  def ocr(line)
    line.pop
    cols = cols_for_line(line)
    translate_cols(cols)
  end

  def cols_for_line(line)
    cols = []
    line.first.length.times do |i|
      cols << line.map { |row| row[i] }
    end
    cols
  end

  def translate_cols(cols)
    cols.each_slice(3).map do |matrix|
      translate_matrix(matrix)
    end.join
  end

  def translate_matrix(matrix)
    dictionary[matrix.flatten] || "?"
  end

  def dictionary
    {
      [" ", "|", "|", "_", " ", "_", " ", "|", "|"] => "0",
      [" ", " ", " ", " ", " ", " ", " ", "|", "|"] => "1",
      [" ", " ", "|", "_", "_", "_", " ", "|", " "] => "2",
      [" ", " ", " ", "_", "_", "_", " ", "|", "|"] => "3",
      [" ", "|", " ", " ", "_", " ", " ", "|", "|"] => "4",
      [" ", "|", " ", "_", "_", "_", " ", " ", "|"] => "5",
      [" ", "|", "|", "_", "_", "_", " ", " ", "|"] => "6",
      [" ", " ", " ", "_", " ", " ", " ", "|", "|"] => "7",
      [" ", "|", "|", "_", "_", "_", " ", "|", "|"] => "8",
      [" ", "|", " ", "_", "_", "_", " ", "|", "|"] => "9"
    }
  end
end
