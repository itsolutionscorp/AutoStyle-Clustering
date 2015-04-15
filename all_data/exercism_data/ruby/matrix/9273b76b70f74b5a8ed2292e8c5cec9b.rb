class Matrix
  attr_reader :data

  def initialize(data)
    @data = data
  end

  def rows
    data.lines.map{ |row| row.split.map(&:to_i) }
  end

  def columns
    rows.transpose
  end

end
