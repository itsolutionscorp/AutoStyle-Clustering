class Matrix

  def initialize(matrix)
    @matrix = matrix
  end

  def rows
    @matrix.split("\n").map do |row|
      row.split(" ").map {|item| item.to_i }
    end
  end

  def columns
    results = []
    current_row = []
    rows.first.length.times.map do |col|
      rows.map do |row|
        current_row << row[col]
      end
      results << current_row
      current_row = []
    end
    results
  end

  def find_maxes(row)
    max_idxs = []
    max_val = row.max
    row.each_with_index do |num, idx|
      if num == max_val
        max_idxs << idx
      end
    end
    max_idxs
  end

  def find_mins(column)
    min_idxs = []
    min_val = column.min
    column.each_with_index do |num, idx|
      if num == min_val
        min_idxs << idx
      end
    end
    min_idxs
  end

  def find_row_maxes
    cols_with_maxes = []
    rows.each_with_index.map do |row, idx|
      find_maxes(row).map do |maxes|
        cols_with_maxes << [idx, maxes]
      end
    end
    cols_with_maxes
  end

  def find_col_mins
    rows_with_mins = []
    columns.each_with_index do |col, idx|
      find_mins(col).map do |mins|
        rows_with_mins << [mins, idx]
      end
    end
    rows_with_mins
  end

  def saddle_points
    saddle_points = find_row_maxes & find_col_mins
  end

end
