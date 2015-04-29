class Matrix
  attr_reader :text, :rows, :columns

  def initialize(input)
    @text = input
    @rows = convert_rows
    @columns = convert_columns
  end

  def convert_rows
    output = []
    text.lines do |line|
      row = []
      line.gsub("\n","").split.each do |element|
        row << element.to_i
      end
      output << row
    end
    output
  end

  def convert_columns
    rows.transpose
  end
end
