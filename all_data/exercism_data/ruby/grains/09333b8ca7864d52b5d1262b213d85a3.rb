class Grains
    def square(board_square)
      power = board_square - 1
      # break out if board_square is 1
      return board_square if board_square == 1 or board_square == 2
      
      # else, return our product
      2 ** power
    end
    
    def total
      (2 ** 64) - 1
    end
end
