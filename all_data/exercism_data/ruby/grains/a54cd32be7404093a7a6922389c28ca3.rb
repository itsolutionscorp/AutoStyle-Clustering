class Grains
  def square(n)
    current_count = 0

    (1..n).each do |current_square|
      current_count = current_square == 1 ? 1 : current_count * 2
    end

    current_count
  end

  def total
    (1..64).inject{|sum, n| sum + square(n)}
  end
end
