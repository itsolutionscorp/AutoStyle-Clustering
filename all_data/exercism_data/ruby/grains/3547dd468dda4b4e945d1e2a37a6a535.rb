class Grains

  def square(number)
    2**(number - 1)
  end

  def total
    total_grains = 0
    64.times do |square|
      total_grains += square(square + 1)
    end
    total_grains
  end
end
