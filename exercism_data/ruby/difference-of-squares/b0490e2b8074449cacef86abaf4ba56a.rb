class AbstractSquares
  attr_reader :sum_of_squares, :square_of_sums

  def initialize(number)
    @sum_of_squares = []
    @square_of_sums = 0
    calc_squares(number)
  end

  def difference
    @square_of_sums - @sum_of_squares
  end

  private
  def calc_squares(n)
    "This is abstract class, you must implement this method in descendent classes"
  end
end

class TimesSquares < AbstractSquares
  private
  def calc_squares(n)
    # iterator starts from 0 so we need n+1 cycles
    (n+1).times do |number|
      @square_of_sums+=number
      @sum_of_squares << number**2
    end
    @sum_of_squares = @sum_of_squares.reduce(:+)
    @square_of_sums**=2
  end
end

class Squares < AbstractSquares
  private
  def calc_squares(n)
    i = 1
    while i <= n 
      @square_of_sums+=i
      @sum_of_squares << i**2
      i+=1
    end
    @sum_of_squares = @sum_of_squares.reduce(:+)
    @square_of_sums**=2
  end
end

module FuncSquares

  def self.new(num)
    if @num != num
      @num = num
      @sum_of_squares = nil
      @square_of_sums = nil
    end
    return self
  end

  def self.difference
    self.square_of_sums - self.sum_of_squares
  end

  def self.sum_of_squares
    return @num if @num.nil?
    @sum_of_squares ||= _sum_of_squares(@num, [])
  end
  def self._sum_of_squares(number, acc)
    if number == 0
      acc.reduce(:+)
    else
      _sum_of_squares(number-1, acc << number**2)
    end
  end

  def self.square_of_sums
    return @num if @num.nil?
    @square_of_sums ||= _square_of_sums(@num, 0)
  end
  def self._square_of_sums(number, acc)
    if number == 0
      acc**2
    else
      _square_of_sums(number-1,acc+number)
    end
  end

end
#benchmarking code
#require 'benchmark/ips'
##require_relative 'squares'
#def run_3_functions(class_name)
  #o = class_name.new(100)
  #o.square_of_sums
  #o.sum_of_squares
  #o.difference
#end

#Benchmark.ips do |x|
  #x.report ( 'times' ) {  run_3_functions(TimesSquares) }
  #x.report ( 'while' ) { run_3_functions(Squares) }
  #x.report ( 'func' ) { run_3_functions(FuncSquares) }
#end
