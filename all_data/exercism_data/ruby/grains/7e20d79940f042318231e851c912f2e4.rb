class Grains
  def square(space)
    2 ** (space - 1)
  end

  def total
    total = 0

    (1..64).each do |space|
      total += square(space)
    end
    
    total
  end
end
