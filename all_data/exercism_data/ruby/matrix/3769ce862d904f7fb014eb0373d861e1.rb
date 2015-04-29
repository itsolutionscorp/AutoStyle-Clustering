class Matrix
  attr_reader :rows, :columns

  def initialize(input)
    @rows = input.split("\n").map{|e| e.split.map(&:to_i)}
    @columns = @rows.transpose
  end
end
