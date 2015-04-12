class Hamming
  def compute(refdna, newdna)
    hammingscore = 0
    a = refdna.scan(/./)
    b = newdna.scan(/./)
    a.each_with_index { |amino, index| hammingscore += 1 if b.length > index && amino != b[index] }
    return hammingscore
  end
end
