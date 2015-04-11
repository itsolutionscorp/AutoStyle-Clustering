class Matrix
  def initialize matrix_string
    @rows = parse matrix_string
    @columns = @rows.transpose
    compute_saddle_points
  end
  
  attr_reader :rows, :columns, :saddle_points
  
  private
  
  def compute_saddle_points
    @saddle_points = []
    
    (0...@rows.count).each do |i|
      (0...@rows[i].size).each do |j|
        crt = rows[i][j]
        if crt == @rows[i].max && crt == @columns[j].min
          @saddle_points << [i, j]
        end
      end
    end
  end
  
  def parse(matrix_str)
    matrix_str.lines.map { |l| l.scan(/\d+/).map &:to_i }
  end
end
