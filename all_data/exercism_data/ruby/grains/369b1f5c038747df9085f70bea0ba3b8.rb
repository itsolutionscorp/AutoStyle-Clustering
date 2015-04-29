class Grains
  @@chessboard_square_to_num_of_grains_map = [1] #there is one grain of rice on the first chessboard square
  
  (0..63).each do |index|
    if index==0
      @@chessboard_square_to_num_of_grains_map[index]=1 #there is one grain of rice on the first chessboard square
    else
      @@chessboard_square_to_num_of_grains_map[index]=@@chessboard_square_to_num_of_grains_map[index-1]*2
    end
  end

  # def initialize
  # end

  def square(inquiry_num)
    return @@chessboard_square_to_num_of_grains_map[inquiry_num-1] #offset for zero index array
  end

  def total
    @@chessboard_square_to_num_of_grains_map.reduce(:+)
  end
end
