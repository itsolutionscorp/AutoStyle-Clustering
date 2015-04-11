class Grains
  def square index
    # 1 - 1 2 ** 0
    # 2 - 2 2 ** 1
    # 3 - 4 2 ** 2
    # 4 - 8 2 ** 3
    # 5 - 16 2 ** 4
    2 ** (index - 1)
  end
  def total
    square(65) - 1
  end
end
