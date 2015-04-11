class Grains

  MAX = 64

  def square(square_num)
    2**(square_num-1)
  end

  def total
    (1..MAX).inject(0) do |memo, n|
      memo + square(n)
    end
  end
end

# 1=>1
# 2=>2
# 3=>4
# 4=>8
