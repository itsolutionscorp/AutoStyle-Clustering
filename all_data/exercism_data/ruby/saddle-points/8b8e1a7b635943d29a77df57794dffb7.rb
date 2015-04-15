class Matrix
  attr_reader :rows, :columns, :saddle_points
  def initialize matstr
    @rows = matstr.lines.collect do |row| 
      row.split.collect(&:to_i)
    end
    @columns = rows.transpose
    @saddle_points = []
    (0...(rows.count)).each do |x|
      (0...(columns.count)).each do |y|
        saddle_points << [x,y] if is_saddle_point? x, y
      end
    end
  end
  
  private
  def is_saddle_point? x, y
    val = rows[x][y]
    rows[x].all? { |check| val >= check } &&
    columns[y].all? { |check| val <= check }
  end
end
