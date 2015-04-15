class Matrix
  def initialize string
    @matrix = parse_matrix string
  end
  
  def rows
    @matrix
  end
  
  def columns
    rows.transpose
  end
  
  private
  def chomp_lines string
    string.lines.map(&:chomp)
  end
  def parse_matrix string
    chomp_lines(string).map{|row| parse_row row}
  end
  def parse_row string
    string.split(' ').map(&:to_i)
  end
end
