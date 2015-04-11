class Grains
  def square(position)
    2 ** (position - 1)
  end

  def total
    holder = []
    (1..64).map {|num| holder << Grains.new.square(num)}
    holder.reduce(:+)
  end
end
