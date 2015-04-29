class Matrix
  def initialize(str)
    @str = str
    @table = build_table
  end

  def rows
    @table
  end

  def columns
    @table.transpose
  end

  def saddle_points
    results = []
    rows.each_with_index do |row, i|
      row_indexes = max_indexes(row)
      row_indexes.each do |j|
        columns[j].each do |el|
          results << [i, j] if columns[j].sort.first == el && row[j] == el
        end
      end
    end
    results.uniq
  end

  private

  def build_table
    @str.split("\n").collect { |i| i.split.map(&:to_i) }
  end

  def max_indexes(vals)
    results = []
    max = vals.max
    vals.each_with_index { |el, i| results << i if el == max }
    results
  end
end
