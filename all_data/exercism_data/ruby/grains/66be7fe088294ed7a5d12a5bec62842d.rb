class Grains
  def square(squares)
    @grains = 1

    i = 1
    while i < (squares)
      @grains = @grains * 2
      i += 1
    end
    @grains
  end

  def total
    square(65)-1
  end
end
