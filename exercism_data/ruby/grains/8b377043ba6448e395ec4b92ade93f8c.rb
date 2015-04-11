class Grains
  def square square_number
    how_many_doublings = square_number - 1  # on square 2, we've doubled once, etc

    2 ** how_many_doublings
  end

  def total implementation = :total_faster
    send implementation
  end

  private

  def total_original
    all_board_numbers = 1..64
    all_board_numbers.inject(0) do |grains, square_number|
      grains += Grains.new.square square_number
    end
  end

  def total_faster
    2 ** 64 - 1
  end

  def total_readable
    number_of_squares = 64
    sum_of_powers_of_2 = lambda { |exponent| 2 ** exponent - 1 }

    sum_of_powers_of_2.call number_of_squares
  end
end



require 'benchmark'
Benchmark.bm 10 do |x|
  [:total_original, :total_faster, :total_readable].each do |implementation|
    x.report("total #{implementation}".ljust 20) { 1000.times do ; Grains.new.total implementation; end }
  end
end

#                  user     system      total        real
# total total_original  0.020000   0.000000   0.020000 (  0.023756)  45X
# total total_faster    0.000000   0.000000   0.000000 (  0.000529)
# total total_readable  0.000000   0.000000   0.000000 (  0.001464)   3X
