class Grains

  def squareX(num)
    result = 1
    (num-1).times { result *= 2 }
    result
  end

  def totalX
    result = 0
    (1..64).each { |i| result += square(i) }
    result
  end

  def squareY(num)
    2 ** ( num -1 )
  end

  def square(num)
    1 << ( num -1 )
  end

  def total
    # so the total is all the space fill with one
    # or 0xffffffffffffffff
    0b1111111111111111111111111111111111111111111111111111111111111111
  end

end

=begin
File: grains_profile.rb

require_relative 'grains'
require 'profiler'

def functionToBeProfiled
  grains = Grains.new
  1000000.times do |i|
    result = grains.squareY(i%64)
  end
end

def functionToBeProfiledX
  grains = Grains.new
  1000000.times do |i|
    result = grains.square(i%64)
  end
end
Profiler__::start_profile
functionToBeProfiled
Profiler__::stop_profile
Profiler__::print_profile($stdout)
puts "--------------------------"
Profiler__::start_profile
functionToBeProfiledX
Profiler__::stop_profile
Profiler__::print_profile($stdout)


ruby grains_profile.rb
  %   cumulative   self              self     total
 time   seconds   seconds    calls  ms/call  ms/call  name
 34.43     4.92      4.92  1000000     0.00     0.01  Grains#squareY
 29.67     9.16      4.24  1000001     0.00     0.03  Object#functionToBeProfiled
 24.21    12.62      3.46        1  3460.00 14290.00  Integer#times
  8.54    13.84      1.22  1031250     0.00     0.00  Fixnum#**
  2.80    14.24      0.40    15625     0.03     0.03  Rational#**
  0.21    14.27      0.03    31250     0.00     0.00  Fixnum#div
  0.14    14.29      0.02    31250     0.00     0.00  Fixnum#-@
  0.00    14.29      0.00        1     0.00     0.00  TracePoint#enable
  0.00    14.29      0.00        1     0.00     0.00  Class#new
  0.00    14.29      0.00        1     0.00     0.00  BasicObject#initialize
  0.00    14.29      0.00        1     0.00     0.00  TracePoint#disable
  0.00    14.29      0.00        1     0.00 14290.00  #toplevel
--------------------------
  %   cumulative   self              self     total
 time   seconds   seconds    calls  ms/call  ms/call  name
 34.30     4.74      4.74  1000000     0.00     0.01  Grains#square
 33.07     9.31      4.57  1000001     0.00     0.02  Object#functionToBeProfiledX
 26.85    13.02      3.71        1  3710.00 13820.00  Integer#times
  5.79    13.82      0.80  1000000     0.00     0.00  Fixnum#<<
  0.00    13.82      0.00        1     0.00     0.00  TracePoint#enable
  0.00    13.82      0.00        1     0.00     0.00  Class#new
  0.00    13.82      0.00        1     0.00     0.00  BasicObject#initialize
  0.00    13.82      0.00        1     0.00     0.00  TracePoint#disable
  0.00    13.82      0.00        1     0.00 13820.00  #toplevel

Result: Looks like << operation a little fast.

=end
