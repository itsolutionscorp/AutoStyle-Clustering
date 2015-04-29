class Grains

  def square(square)
    @grains = 1
    until square == 1 do
      @grains = @grains * 2
      square -= 1
    end
    @grains
  end

  def total
    grains = []
    result = 0
    (1..64).each { |sq| grains << square(sq) }
    grains.each { |g| result += g }
    result
  end

end
