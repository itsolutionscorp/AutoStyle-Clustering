class Grains
  def square(n)
    grains = 1

    while n > 1
      grains = grains * 2
      n = n - 1
    end

    grains
  end

  def total
    total = 0

    (1..64).each do |i|
      total += square(i)
    end

    total
  end
end
