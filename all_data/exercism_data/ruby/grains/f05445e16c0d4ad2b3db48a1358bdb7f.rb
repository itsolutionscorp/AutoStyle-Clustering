class Grains
  def square(num)
    num == 1 ? num : square(num - 1) * 2
  end
end
