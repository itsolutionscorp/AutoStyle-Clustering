class Grains
  def square(number)
    2**(number-1)
  end

  #more consise and might be slightly faster
  def total
    (1..64).inject {|sum, num| sum + square(num)}
  end

=begin
  # seems to be about the same as below but slightly clearer in the
  # context of the problem
  def total
    sum = 0
    (1..64).each {|num| sum += square(num)}
    sum
  end
=end

=begin
  def total
    sum = 0
    64.times {|num| sum += square(num + 1)}
    sum
  end
=end
end
