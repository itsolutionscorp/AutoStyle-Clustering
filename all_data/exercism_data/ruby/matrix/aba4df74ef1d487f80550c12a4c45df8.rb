class Matrix
  attr_reader :rows, :columns

  def initialize input
    @rows = input.split("\n").each_with_object([]) do |row, lst|
      lst << row.split(" ").map { |c| c.to_i }
    end
    @columns = @rows[0].zip(*@rows.slice(1..-1))
  end

end
