class Matrix
  attr_reader :rows

  def initialize input
    @rows = scan input
  end

  def columns
    @columns ||= @rows.transpose
  end

  def saddle_points
    @rows.length.times.each_with_object([]) do |r, points|
      columns.length.times do  |c|
        points << [r, c] if saddle?(r, c)
      end
    end
  end

private
  def scan text
    text.lines.map { |r| r.split.map(&:to_i) }
  end

  def saddle? r, c
    columns[c].min == @rows[r].max && 
      @rows[r].max == @rows[r][c]
  end
end
