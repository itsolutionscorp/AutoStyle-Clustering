class Grains

  def square(number)
    return number if number < 3
    2 ** (number - 1)
  end

  def total(squares = 64)
    grains(squares).inject(:+)
  end

  private

  def grains(squares = 64)
    @grains ||= [0]
    @grains += calc_grains_for(@grains.length..squares)
  end

  def calc_grains_for(squares = [])
    squares.map do |number|
      square(number)
    end
  end
end
