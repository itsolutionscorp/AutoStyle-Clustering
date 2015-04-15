class Hamming
  def self.compute(left, right)

    ln = [left.length, right.length].min

    l_chars =  left[0...ln].chars
    r_chars = right[0...ln].chars

    l_chars.zip(r_chars)
           .select { |l,r| l != r }
           .count
  end
end
