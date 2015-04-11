class Grains

  def square(grains)
    2**(grains-1)
  end

  def total
    total = 0
    (1..64).each do |count|
      total+= square(count)
    end
    total
  end

end
