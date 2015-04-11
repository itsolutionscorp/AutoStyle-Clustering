# tercero rubocop
class Grains
  def square(posi)
    (2**posi) / 2
  end

  def total
    acum = 0
    i = 1
    while i <= 64
      acum = acum + square(i)
      i = i + 1
    end
    acum
  end
end
