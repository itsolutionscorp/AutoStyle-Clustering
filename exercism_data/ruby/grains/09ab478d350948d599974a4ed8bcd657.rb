class Grains
  def square(position)
    (1..position).inject { |result, position| result * 2 }
  end

  def total(position=64)
    (1..position).inject {|sum, position| square(position) + sum}

  end
end
