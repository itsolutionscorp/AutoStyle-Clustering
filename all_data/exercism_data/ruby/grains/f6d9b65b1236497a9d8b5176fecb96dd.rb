class Grains
  def square(sq_num)
    @sq_num = sq_num
    2**(@sq_num-1)
  end

  def total
    i = 1
    total = 0
    while i < 65 do
      total = total + 2**(i-1)
      i+=1
    end
    total
  end
end
