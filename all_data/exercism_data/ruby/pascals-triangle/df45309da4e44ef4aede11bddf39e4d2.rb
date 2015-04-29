class Triangle
  def initialize(r)
    @r = r
  end

  def rows
    rows = [[1]]
    return rows if @r == 1
    rows << [1, 1]
    2.upto(@r - 1) do |i|
      rows << [1] + rows[i - 1].each_cons(2).map { |s| s.reduce(:+) } + [1]
    end
    rows
  end
end
