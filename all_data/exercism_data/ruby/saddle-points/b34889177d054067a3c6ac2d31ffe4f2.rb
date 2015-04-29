class Matrix
  attr_reader :text, :grid

  def initialize(text)
    @text = text
    @grid ||= setup_grid
  end

  def rows
    grid
  end

  def columns
    grid.transpose
  end

  def saddle_points
    [].tap do |ary|
      each_with_indices do |val, r, c|
        ary << [r,c] if row_max?(val, r) && col_min?(val,c)       
      end
    end
  end

  private

  def setup_grid
    text.each_line.map do |line| 
      line.split.map(&:to_i)
    end
  end

  def row_max?(value, index)
    value == rows[index].max
  end

  def col_min?(value, index)
    value == columns[index].min
  end

  def each_with_indices
    grid.each_index do |r|
      grid[r].each_with_index { |value, c| yield(value,r,c) }
    end
  end
end
