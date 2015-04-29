class Matrix
  attr_reader :rows, :columns

  def initialize str
    @rows = determine_rows str
    @columns = rows.transpose
  end

  private

  def determine_rows str
    str.split("\n").map { |l| l.split(' ').map(&:to_i) }
  end
end
