#Alternative method in an attempt to speed it up. Did not work

class Grains  
  @@array= Array.new(64){|i|2**(i)}
  attr_reader :array
#  puts @@array
  def square(yum)
     @@array[yum-1]
  end
  def total
    @@array.inject(:+)
  end
end
