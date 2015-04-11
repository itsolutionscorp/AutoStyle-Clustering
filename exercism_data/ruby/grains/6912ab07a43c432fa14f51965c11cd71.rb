class Grains
  def square(square)
    @total_squares = 1
    starting_square = 1
    grains = 1
    while starting_square < square
      grains = grains * 2
      starting_square += 1
      @total_squares += grains
    end
    grains
  end

  def total
    square(64)
    @total_squares
  end
end











#def square(square)
#  @total = 1
#  i = 1
#  wheat = 1
#  while i < square
#    wheat = wheat * 2
#    @total += wheat
#    i += 1
#  end
#  wheat
#end
#def total
#  square(64)
#  @total
#end
