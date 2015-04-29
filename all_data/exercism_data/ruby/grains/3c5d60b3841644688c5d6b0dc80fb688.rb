class Grains
  TABLE = {}

  TABLE['total'] = (1..64).reduce(0) do |memo, n|
    memo + TABLE[n] = 2**(n-1)
  end

  def square(n)
    TABLE[n]
  end

  def total
    TABLE['total']
  end
end
