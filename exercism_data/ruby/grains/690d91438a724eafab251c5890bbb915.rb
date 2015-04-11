class Grains
  def square(num)
    (1..num).inject do |grains|
      grains * 2
    end
  end

  def total
    (1..64).inject(0) do |grains, num|
      grains + square(num)
    end
  end
end
