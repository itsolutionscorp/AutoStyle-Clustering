class Grains
  def square(num)
    s = 0

    1.upto(num) do |p,n|
      s = s > 0 ? (2 * s) : 1
    end

    s
  end

  def total
    (square(64) * 2) - 1
  end
end
