class Triangle
  attr_reader :rows

  def initialize(n)
    @rows = n.pred.times.reduce([[1]]) do |rows|
      rows << [0, *rows.last, 0].each_cons(2).map { |a, b| a + b }
    end
  end
end
