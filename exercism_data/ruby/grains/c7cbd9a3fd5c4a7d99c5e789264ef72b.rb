class Grains
  def square(num)
    (1..num - 1).inject(1) { |grain| grain *= 2 }
  end

  def total
    square(65) - 1
  end
end
