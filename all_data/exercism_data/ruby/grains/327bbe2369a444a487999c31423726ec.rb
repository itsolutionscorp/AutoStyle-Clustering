require 'benchmark'

class Grains 
  @@squares = [1]

  def square(e)
    @@squares[e - 1] ||= square(e - 1) * 2
  end

  def total(range = 1..64)
    square(range.last + 1) - square(range.first)
  end
end

# speed testing
run = false
if(run)
  Benchmark.bmbm do |bm|
    test_times = 1000
    num_of_squares = [64, 200, 500, 1000]

    num_of_squares.each do |n|    
      bm.report("total(0...#{n})".rjust(25, ' ') + "running #{test_times} times:") do
        test_times.times { Grains.new.total(0...n) }
      end
    end
  end
end

# Results
=begin
Rehearsal --------------------------------------------------------------------------------
            total(0...64)running 1000 times:   0.000000   0.000000   0.000000 (  0.001500)
           total(0...200)running 1000 times:   0.000000   0.000000   0.000000 (  0.001000)
           total(0...500)running 1000 times:   0.000000   0.000000   0.000000 (  0.001500)
          total(0...1000)running 1000 times:   0.000000   0.000000   0.000000 (  0.002000)
----------------------------------------------------------------------- total: 0.000000sec

                                                   user     system      total        real
            total(0...64)running 1000 times:   0.000000   0.000000   0.000000 (  0.001000)
           total(0...200)running 1000 times:   0.000000   0.000000   0.000000 (  0.001000)
           total(0...500)running 1000 times:   0.000000   0.000000   0.000000 (  0.001000)
          total(0...1000)running 1000 times:   0.000000   0.000000   0.000000 (  0.001500)
=end          
