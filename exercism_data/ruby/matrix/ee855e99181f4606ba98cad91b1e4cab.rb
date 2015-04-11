class Matrix
  attr_reader :text, :rows, :columns

  def initialize(input)
    @text = input
  end

  def rows
    text.split("\n").map do |element|
      element.split.map do |item|
        item.to_i
      end
    end
  end

  def columns
    rows.transpose
  end
end
