class Matrix
  attr_reader :rows, :columns

  def initialize(str)
    @rows = str.split("\n").map do |row|
      row.split(" ").map(&:to_i)
    end
    @columns = rows.transpose
  end

  def saddle_points
    row_maxes & column_mins
  end

  private

  def local_indexes(collection, _method)
    collection.each_with_object([]).with_index do |(c_row, collector), c_index|
      c_row.send(_method).tap do |match|
        c_row.each_with_index do |element, index|
          collector << (yield c_index, index) if element == match
        end
      end
    end
  end

  def row_maxes
    local_indexes(rows, :max) { |row, col| [row, col] }
  end

  def column_mins
    local_indexes(columns, :min) { |col, row| [row, col] }
  end
end
