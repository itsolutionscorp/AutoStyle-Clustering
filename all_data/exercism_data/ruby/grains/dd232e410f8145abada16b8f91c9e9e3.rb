require 'benchmark'

class Grains
  def initialize
    @base = 2
  end

  def square(e)
    @base**(e - 1)
  end

  def total(range = 0...64)
    range.map { |e| 2**e }.inject(:+)
  end

  # can it be shorter?
  def total_faster(range = 0...64)
    sum, add = 0, 1
    range.count.times do
      sum += add
      add *= 2
    end    
    sum
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

      bm.report("total_faster(0...#{n})".rjust(25, ' ') + "running #{test_times} times:") do
        test_times.times { Grains.new.total_faster(0...n) }
      end
    end
  end
end

# Speed Testing Results:
=begin
Rehearsal --------------------------------------------------------------------------------
            total(0...64)running 1000 times:   0.078000   0.000000   0.078000 (  0.074004)
     total_faster(0...64)running 1000 times:   0.047000   0.000000   0.047000 (  0.049003)
           total(0...200)running 1000 times:   0.468000   0.000000   0.468000 (  0.468027)
    total_faster(0...200)running 1000 times:   0.265000   0.000000   0.265000 (  0.268015)
           total(0...500)running 1000 times:   1.965000   0.000000   1.965000 (  1.975113)
    total_faster(0...500)running 1000 times:   0.812000   0.000000   0.812000 (  0.810046)
          total(0...1000)running 1000 times:   5.007000   0.000000   5.007000 (  5.099292)
   total_faster(0...1000)running 1000 times:   1.841000   0.016000   1.857000 (  1.855106)
---------------------------------------------------------------------- total: 10.499000sec

                                                   user     system      total        real
            total(0...64)running 1000 times:   0.078000   0.000000   0.078000 (  0.079005)
     total_faster(0...64)running 1000 times:   0.047000   0.000000   0.047000 (  0.049003)
           total(0...200)running 1000 times:   0.483000   0.000000   0.483000 (  0.519029)
    total_faster(0...200)running 1000 times:   0.266000   0.000000   0.266000 (  0.261015)
           total(0...500)running 1000 times:   1.996000   0.000000   1.996000 (  2.032117)
    total_faster(0...500)running 1000 times:   0.780000   0.000000   0.780000 (  0.802046)
          total(0...1000)running 1000 times:   5.242000   0.000000   5.242000 (  5.262301)
   total_faster(0...1000)running 1000 times:   1.841000   0.000000   1.841000 (  1.912109)
=end
