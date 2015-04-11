class Matrix
  attr_reader :rows

  def initialize(string)
    @rows = string.lines.map{ |line| line.split.map(&:to_i) }
  end
  
  def columns
    @rows.transpose
  end


end
