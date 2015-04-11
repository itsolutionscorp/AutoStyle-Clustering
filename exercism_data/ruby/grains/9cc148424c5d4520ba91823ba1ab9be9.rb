class Grains
  
    # create an array of 64 hashes. each hash key will be the number of grains
    # on any given square, each hash value will keep a running tally 
    # of all the grains on the chessboard.

      @@grid = Array.new
      grains_so_far = 0
  
     (0..63).each_with_object([]){|ndx, pairs| pairs = Hash.new
         (pairs[2**(ndx)] = (grains_so_far += 2**(ndx)))
         puts pairs.inspect
         @@grid << pairs
      }
  
  
  def square(which_square)
    @@grid[which_square - 1].each{|k,v| return k}
  end
  
  def total
    @@grid.last.each{|k,v| return v}
  end
end
