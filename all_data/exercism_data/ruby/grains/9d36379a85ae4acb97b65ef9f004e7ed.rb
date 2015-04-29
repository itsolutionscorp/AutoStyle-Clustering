class Grains
  def square(number)
    2 ** (number - 1)
  end

  def total
    # 1.upto(64).inject(0) { |sum, number| sum + square(number) }
    2 ** 64 - 1
  end
end
