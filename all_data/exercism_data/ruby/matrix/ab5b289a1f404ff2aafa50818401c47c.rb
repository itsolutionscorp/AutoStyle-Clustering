class Matrix
  attr_reader :text, :rows, :columns
  
  def initialize(input)
    @text = input
    @rows = convert_rows
    @columns = convert_columns
  end
  
  def convert_rows
    output = []
    text.split("\n").each do |element| 
      row = []
      element.split(" ").each do |n|
        row.push(n.to_i)
      end
      output.push(row)
    end
    output
  end

  def convert_columns
    output = []
    (0..rows[0].length-1).each do |i|
      row = []
      (0..rows.length-1).each do |j|
        row.push(rows[j][i])
      end
      output.push(row)
    end
    output
  end
end
