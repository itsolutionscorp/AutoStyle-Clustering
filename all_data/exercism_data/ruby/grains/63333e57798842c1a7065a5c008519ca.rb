class Grains
  
    # create a Class variable array whose index runs from 1 to 64. Each element stored there will
    # be a hash. the key will be the number of grains on any given square, the value will
    # keep a running tally of all the grains on the chessboard.
    
      @@count_the_grains_on = Array.new
      this_square = Hash.new
      grains_so_far = 0
      for ndx in 1..64
        this_square = Hash.new
        grains_this_square = 2**(ndx - 1)
        grains_so_far += grains_this_square
        this_square[grains_this_square] = grains_so_far
        @@count_the_grains_on[ndx] = this_square
      end
  
  def initialize    
  end
  
  
  def square(which_square)
    #  retrieve the nth element of the array, return the key:
    @@count_the_grains_on[which_square].each{|k,v| return k}
  end
  
  def total
    # retrieve the last element in the array, return the value:
    @@count_the_grains_on[-1].each{|k,v| return v}
  end
end
