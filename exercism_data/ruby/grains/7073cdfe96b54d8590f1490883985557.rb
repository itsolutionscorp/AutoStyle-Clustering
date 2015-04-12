class Grains
  def square(square)
    square_hash = { 1 => 1 }
    (1..square).each do |x|
      square_hash[x] = 2 * square_hash[x - 1] unless x == 1
    end
    square_hash[square]
  end

  def total
    sum = 0
    (1..64).each do |x|
      sum += square(x)
    end
    sum
  end
end