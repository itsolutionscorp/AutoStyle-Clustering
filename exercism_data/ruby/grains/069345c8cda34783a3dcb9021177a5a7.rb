class Grains
  def square(num)
    (2..num).inject(1) do |grains, num|
      grains += grains
    end
  end

  def total
    (1..64).inject(0) do |grains, num|
      grains + square(num)
    end
  end
end
