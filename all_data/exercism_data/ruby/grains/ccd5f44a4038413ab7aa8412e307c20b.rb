class GrainsReadability
  def square n
    2 ** (n - 1)
  end

  def total
    (1..64).map{|n| square n}.reduce :+
  end
end

class GrainsPerformance
  @@square_values = { 1 => 1}

  def square n
    @@square_values[n] ||= compute_square(n)
  end

  def total
    sum = 0
    i = 1
    (1..64).each_with_object(0){
      sum += i
      i *= 2
    }
    sum
  end

  private 
  
  def compute_square n
    square(n-1) * 2
  end
end

def perf_test
  require 'benchmark'

  Benchmark.bm(10){ |x|
    x.report('readable')   { 1000.times { GrainsReadability.new.total }}
    x.report('performant') { 1000.times { GrainsPerformance.new.total }}
  }
=begin
                 user     system      total        real
readable     0.031000   0.000000   0.031000 (  0.032022)
performant   0.016000   0.000000   0.016000 (  0.017012)
=end

end

# perf_test

Grains = GrainsPerformance
