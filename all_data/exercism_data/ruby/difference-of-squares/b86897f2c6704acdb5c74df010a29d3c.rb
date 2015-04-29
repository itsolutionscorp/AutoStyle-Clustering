class Squares
  attr_reader :square_of_sums 
  attr_reader :sum_of_squares
  attr_reader :difference

  def initialize number
    @square_of_sums = (number * (number + 1) / 2)**2
    @sum_of_squares = number * (number + 1) * (2 * number + 1)
    @difference = @square_of_sums - @sum_of_squares
  end

end



### Benchmarks for comparing eager to lazy evaluation versions.
require 'benchmark'
class Squares_V2
  attr_reader :square_of_sums 
  def initialize number
    @square_of_sums = (number * (number + 1) / 2)**2
  end
end

ITERATIONS = 1000000

def run(squares, number, bench)
  bench.report("#{squares.name} for #{number}") do
    ITERATIONS.times do
      squares.new(number).square_of_sums
    end
  end
end

Benchmark.bm do |bench|
  run(Squares, 2**2, bench)
  run(Squares, 2**4, bench)
  run(Squares, 2**8, bench)
  run(Squares, 2**16, bench)
  run(Squares, 2**32, bench)
  run(Squares, 2**64, bench)
  run(Squares, 2**128, bench)
  puts ""
  puts ""
  run(Squares_V2, 2**2, bench)
  run(Squares_V2, 2**4, bench)
  run(Squares_V2, 2**8, bench)
  run(Squares_V2, 2**16, bench)
  run(Squares_V2, 2**32, bench)
  run(Squares_V2, 2**64, bench)
  run(Squares_V2, 2**128, bench)
end

#  *** Results ***
#
#  Squares for 2**2    0.560000   0.000000   0.560000 (  0.563751)
#  Squares for 2**4    0.570000   0.000000   0.570000 (  0.562213)
#  Squares for 2**8    0.560000   0.000000   0.560000 (  0.562885)
#  Squares for 2**16   0.850000   0.000000   0.850000 (  0.856590)
#  Squares for 2**32   1.500000   0.000000   1.500000 (  1.496058)
#  Squares for 2**64   2.330000   0.000000   2.330000 (  2.336650)
#  Squares for 2**128  2.650000   0.000000   2.650000 (  2.655205)


#  Squares_V2 for 2**2    0.420000   0.000000   0.420000 (  0.425817)
#  Squares_V2 for 2**4    0.430000   0.000000   0.430000 (  0.423586)
#  Squares_V2 for 2**8    0.420000   0.000000   0.420000 (  0.423743)
#  Squares_V2 for 2**16   0.640000   0.000000   0.640000 (  0.638307)
#  Squares_V2 for 2**32   0.960000   0.000000   0.960000 (  0.966448)
#  Squares_V2 for 2**64   1.220000   0.000000   1.220000 (  1.211653)
#  Squares_V2 for 2**128  1.420000   0.000000   1.420000 (  1.430537)
