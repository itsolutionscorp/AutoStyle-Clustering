class Grains
  def square(n)
    2 ** (n - 1)
  end

  def total
    totes = 0

    (1..64).each do |n|
      totes += square(n)
    end

    totes
  end
end
