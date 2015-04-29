class Grains

  def total
    total = 0
    Array(1..64).each do |n|
      total+= square(n)
    end
    total
  end

  def square(n)
    1 << n-1
  end
end
