class Matrix
  attr_reader :text, :rows, :columns

  def initialize(input)
    @text = input
    @rows = convert_rows
    @columns = convert_columns
  end

  def convert_rows
    text.split("\n").map do |element|
      element.split.map! do |item|
        item.to_i
      end
    end
  end

  def convert_columns
    rows.transpose
  end
end
