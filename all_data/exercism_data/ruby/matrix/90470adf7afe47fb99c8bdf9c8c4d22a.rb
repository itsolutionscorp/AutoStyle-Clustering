class Matrix
  attr_reader :rows, :columns
  def initialize matstr
    @rows = matstr.split("\n").map(&:split).each { |row| row.map!(&:to_i) }
    @columns = @rows.transpose
  end
end
