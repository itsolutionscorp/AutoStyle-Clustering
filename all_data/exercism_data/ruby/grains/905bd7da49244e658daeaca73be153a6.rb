class Grains

  def square(square_number)
    1 << (square_number - 1)
  end

  def total(max_square = 64)
    ((2 << (max_square - 1))) - 1
  end
end

class LoopGrains

  def number_of_grains(square)
    1 << (square - 1)
  end

  def total(max_square=64)
    1.upto(max_square).reduce { |total, next_square|
      total + number_of_grains(next_square)
    }
  end
end

if __FILE__ == $0
  require 'benchmark'
  SAMPLES = 100
  Benchmark.benchmark(Benchmark::CAPTION, 7, Benchmark::FORMAT, "total", "average") do |bm|
    reports = []
    SAMPLES.times do
      reports << bm.report("shifting 65536") { Grains.new.total 65536 }
    end
    [reports.inject(:+), (reports.inject(:+)) / SAMPLES]
  end

  Benchmark.benchmark(Benchmark::CAPTION, 7, Benchmark::FORMAT, "total", "average") do |bm|
    reports = []
    SAMPLES.times do
      reports << bm.report("looping 65536") { LoopGrains.new.total 65536 }
    end
    [reports.inject(:+), (reports.inject(:+)) / SAMPLES]
  end
end
