class Grains

   def build_board(square_num)
     beans_per_square = [1]
     until beans_per_square.count == square_num do
       beans = beans_per_square.last * 2
       beans_per_square.push(beans)
     end
     return beans_per_square
   end 
 
   def square(square_num)
     beans_per_square = build_board(square_num)
     return beans_per_square.last
   end

  def total
    beans_per_square = build_board(64)
    return beans_per_square.inject(:+)
  end

end 
