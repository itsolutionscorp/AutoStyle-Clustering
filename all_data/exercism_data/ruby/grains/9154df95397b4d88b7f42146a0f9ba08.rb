require "benchmark"

class Grains
  def square(square)
    (2 ** square) / 2
  end

  def total
    total_with_inject
  end

  def total_with_inject
    (1..64).inject {|sum, number| sum + square(number)}
  end

  def total_with_manual_loop
    sum = 0
    (1..64).each do |number|
      sum += square(number)
    end
  end

  def benchmark
    n = 1_000_000

    #     user     system      total        real
    # 0.240000   0.000000   0.240000 (  0.239673)
    # 0.200000   0.000000   0.200000 (  0.199827)
    Benchmark.bm do |x|
      x.report { n.times do; total_with_inject; end }
      x.report { n.times do; total_with_manual_loop; end }
    end
  end
end
