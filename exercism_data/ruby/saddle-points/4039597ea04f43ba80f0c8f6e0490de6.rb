class Matrix

  attr_accessor :rows, :columns

  def initialize(matrix)
    @rows = matrix.split("\n").map do |row|
      row.split(" ").map {|item| item.to_i }
    end
    @columns = @rows.transpose
  end

  def get_indices_for_value(row_or_col, max_or_min)
    idxs = []
    if max_or_min == "max"
      deciding_value = row_or_col.max
    else
      deciding_value = row_or_col.min
    end
    row_or_col.each_with_index do |num, idx|
      if num == deciding_value
        idxs << idx
      end
    end
    idxs
  end

  def find_max_or_min(row_or_col, max_or_min)
    extreme_values = []
    row_or_col.each_with_index.map do |r_or_c, idx|
      get_indices_for_value(r_or_c, max_or_min).map do |extreme|
        if max_or_min == "max"
          extreme_values << [idx, extreme]
        else
          extreme_values << [extreme, idx]
        end
      end
    end  
    extreme_values
  end

  def saddle_points
    find_max_or_min(rows, "max") & find_max_or_min(columns, "min")
  end

end
