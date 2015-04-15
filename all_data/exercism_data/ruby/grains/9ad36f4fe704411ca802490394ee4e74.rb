class Grains
def initialize
  @array = []
def square(number)

  @counter = 1
  @array.push(@counter)
  (number - 1).times do
  @counter *= 2
  @array.push(@counter)
  end
  @counter
end
def total
  sum = 0
  square(64)
  @array.each do |i|
  sum += i 

  end
  sum
end

end
end
