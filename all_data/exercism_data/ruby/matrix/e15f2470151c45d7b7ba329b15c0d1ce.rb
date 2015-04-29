class Matrix
  def initialize(string)
    @matrix = string.split("\n").map{ |s| s.split(" ").map(&:to_i) }
  end
  
  def rows
    @matrix
  end

  def columns
    @matrix.transpose
  end


end
