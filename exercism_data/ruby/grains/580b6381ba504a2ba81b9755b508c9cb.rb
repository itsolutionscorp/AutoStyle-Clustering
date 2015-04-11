class Grains

  def square(num)
    return 1 if num == 1
    return square(num - 1) * 2
  end

  def total
    (1..64).inject do |r, n|
      r += square(n)
    end
  end
  
end
