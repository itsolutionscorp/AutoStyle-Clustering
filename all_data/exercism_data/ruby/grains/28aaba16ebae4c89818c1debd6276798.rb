class Grains
  def square(sq_num)
    @sq_num = sq_num
    2**(@sq_num-1)
  end

  def num_grains
    current_sq = 1
    num_grains = 0
    while current_sq < 65 do
      num_grains = num_grains + 2**(current_sq-1)
      current_sq+=1
    end
    num_grains  
  end
end
