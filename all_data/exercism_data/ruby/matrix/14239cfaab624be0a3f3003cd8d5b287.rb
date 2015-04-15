class Matrix
  attr_reader :rows, :columns
  def initialize matstr
    @rows = matstr.split("\n").map(&:split).each { |row| row.map!(&:to_i) }
    @columns = (0...@rows.first.length).map { |col_index| rows.inject([]) { |col, row| col.push(row[col_index]) } }
  end
end
