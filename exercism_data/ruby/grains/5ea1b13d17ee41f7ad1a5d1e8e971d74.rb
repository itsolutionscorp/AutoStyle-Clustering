class Grains
  SIZE = 64

  def square n
    2 ** ( n - 1 )
  end

  def total
    2 ** ( SIZE ) - 1
  end
end
