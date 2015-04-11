class Matrix
  def initialize (str)
    lines = str.split("\n")
    @n = lines.size
    @mat = Array.new(@n){Array.new}
    lines.each_with_index{|l, i| l.split.each{|k| @mat[i] << k.to_i}}
  end
  def rows
    -> i {@mat[i]}
  end
  def columns
    -> j {@mat.collect{|row| row[j]}}
  end
end
