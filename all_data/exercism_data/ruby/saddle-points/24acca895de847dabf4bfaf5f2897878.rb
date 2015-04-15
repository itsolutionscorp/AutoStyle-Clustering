class Matrix
  attr_reader :rows, :columns

  def initialize(plaintext)
    @rows = plaintext.split("\n").map do |line|
      line.split(' ').map(&:to_i)
    end
    @columns = @rows.transpose
  end

  def saddle_points
    rows.map.with_index do |row, rnum|
      columns.map.with_index do |col, cnum|
        [rnum, cnum] if row.max == col.min
      end.compact
    end.flatten(1)
  end
end
