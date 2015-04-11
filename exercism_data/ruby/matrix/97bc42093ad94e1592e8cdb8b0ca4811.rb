class Matrix

  def initialize (num)
    @num = num.split("\n")
  end

  def rows
    @num.map { |i| i.split(" ").map { |n| n.to_i } }
  end

  def columns
    rows.transpose
  end
end
