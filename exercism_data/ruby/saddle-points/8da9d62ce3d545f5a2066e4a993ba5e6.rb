class Matrix
  def initialize (str)
    lines = str.split("\n")
    @n = lines.size
    @mat = Array.new(@n){Array.new}
    lines.each_with_index{|l, i| l.split.each{|k| @mat[i] << k.to_i}}
    @m = @mat[0].size
  end
  def rows
    -> i {@mat[i]}
  end
  def columns
    -> j {@mat.collect{|row| row[j]}}
  end
  def saddle_points
    (0...@n).to_a.product((0...@n).to_a).reduce([]){|a, v|
      saddle?(v[0], v[1]) ? (a + [v]) : a
    }
  end
  def saddle? (i, j)
    @mat[i][j] == rows[i].max and @mat[i][j] == columns[j].min
  end
end
