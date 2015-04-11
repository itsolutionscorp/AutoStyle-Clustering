class Matrix

  def initialize (num)
    @num = num
  end

  def rows
    row_array = []
    @num = @num.split("\n") if @num.is_a? String
    @num.each { |pair| row_array << pair.split(" ") }
    row_array.map do |pair|
      pair.map do |letter|
        letter.to_i
      end
    end
  end

  def columns
    column_array = []
    rows.each_with_index do |_, index|
      col_collect = []
      rows.each do |row|
        col_collect << row[index]
      end
      column_array << col_collect
    end
    column_array
  end
end
