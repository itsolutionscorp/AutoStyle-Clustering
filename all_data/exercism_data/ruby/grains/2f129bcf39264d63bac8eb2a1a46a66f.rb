class Grains
  def square(n)
    place_grains(n).last
  end

  def total
    place_grains(64).inject(:+)
  end

  private

  def place_grains(n)
    grains = [1]

    1.upto(n-1) do
      grains << (grains.last * 2)
    end

    grains
  end
end
