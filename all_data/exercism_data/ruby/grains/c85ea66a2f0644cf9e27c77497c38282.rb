# encoding: UTF-8

class Grains
  def square(n)
    board(n).pop
  end

  def total
    t = 0
    board(64).each { |e| t += e }
    t
  end

  private

  def board(squares)
    squares = squares-1
    grains = [1]

    squares.times { grains << grains[-1] * 2 }

    grains
  end
end
