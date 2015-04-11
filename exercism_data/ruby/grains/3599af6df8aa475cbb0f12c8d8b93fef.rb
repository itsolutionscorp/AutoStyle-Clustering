class Grains
  def square(n)
    2**n.pred
  end

  def total
    (2**64).pred
  end
end

class GrainsWithNarrative
  def square(n)
    1.upto(n).reduce { |a, _e| a * 2 }
  end

  def total
    1.upto(64).reduce { |a, e| a + square(e) }
  end
end
