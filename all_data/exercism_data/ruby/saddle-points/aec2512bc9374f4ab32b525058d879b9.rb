require 'set'

class Matrix
  attr_reader :rows, :columns

  def initialize(string)
    @rows = string.split("\n").map { |row|
      row.split.map(&:to_i)
    }
    @columns = @rows.transpose
  end

  def saddle_points
    row_maxes.intersection(column_mins).to_a
  end

  private

  def column_mins
    Set.new @columns.flat_map.with_index { |column, column_index|
      min_indices(column).map { |row_index|
        [row_index, column_index]
      }
    }
  end

  def row_maxes
    Set.new @rows.flat_map.with_index { |row, row_index|
      max_indices(row).map { |column_index|
        [row_index, column_index]
      }
    }
  end

  def min_indices(numbers)
    extreme_indices(numbers, :<)
  end

  def max_indices(numbers)
    extreme_indices(numbers, :>)
  end

  def extreme_indices(array, comparator)
    most_extreme_value = array.first
    array.each.with_index.with_object([]) do |(n, i), indices|
      if n.__send__(comparator, most_extreme_value)
        most_extreme_value = n
        indices.clear
        indices << i
      elsif n == most_extreme_value
        indices << i
      end
    end
  end
end
