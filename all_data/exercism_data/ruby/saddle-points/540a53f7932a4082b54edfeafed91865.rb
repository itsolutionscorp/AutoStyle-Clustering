class Matrix
  include Enumerable

  def initialize(string)
    @raw_data = string
  end

  def rows
    @rows ||= @raw_data.lines.map { |r| r.split(" ").map(&:to_i) }
  end

  def columns
    @columns ||= rows.transpose
  end

  def saddle_points
    @saddle_points ||= set_saddle_points
  end

  def each(by_row = true)
    outer = by_row ? rows : columns
    outer.each_with_index do |inner, outer_index|
      inner.each_with_index do |value, inner_index|
        pos = by_row ? [outer_index, inner_index] : [inner_index, outer_index]
        yield value, *pos
      end
    end
  end

  private

  def set_saddle_points
    each_with_object([]) do |(value, *pos), result|
      result << pos if is_saddle_point?(value, *pos)
    end
  end

  def is_saddle_point?(value, row_index, col_index)
    value == rows[row_index].max && value == columns[col_index].min
  end

end
