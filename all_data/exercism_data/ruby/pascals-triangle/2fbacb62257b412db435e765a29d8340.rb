class Triangle
  attr_reader :rows
  def initialize size
    r = Array.new(size) { Array.new size, 0 }
    r[0][0] = 1
    (1...size).each do |i|
      (0...size).each do |j|
        r[i][j] = r[i - 1][j] + r[i - 1][j - 1]
      end
    end
    @rows = r.map! { |row| row.select { |x| x > 0 } }
  end
end
