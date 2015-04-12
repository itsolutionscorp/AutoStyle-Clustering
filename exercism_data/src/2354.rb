class Hamming
  def compute(one, two)
    first = one.split('')
    second = two.split('')
    count = 0
    first.zip(second).each  {|x, y| count += 1 if x != y }  
    count
  end
end
