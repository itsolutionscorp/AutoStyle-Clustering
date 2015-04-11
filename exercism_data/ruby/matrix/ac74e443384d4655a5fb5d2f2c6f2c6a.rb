class Matrix
  def initialize(input)
    @input = input.split(/\n/)
  end

  def rows
    @input.map do |row|
      row.split(" ").map(&:to_i)
    end
  end

  def columns
    rows.transpose
  end
end
