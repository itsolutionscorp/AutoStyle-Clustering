class Grains
  def square(num)
    2 ** (num - 1)
  end

  def total
    (1..64).inject(0) do |memo, num|
      memo + square(num)
    end
  end
end
