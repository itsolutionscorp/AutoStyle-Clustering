class Grains
  def square(num)
    num == 1 ? num : 2 * square(num-1)
  end

  def total
    (1..64).inject {|acc, num| acc+square(num) }
  end
end
