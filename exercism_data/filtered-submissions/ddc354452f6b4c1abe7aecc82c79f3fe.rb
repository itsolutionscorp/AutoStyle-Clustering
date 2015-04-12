class Hamming
  def compute(a, b)
    return nil if a.length != b.length
    diffs = 0
    a.each_char.with_index do |e, i|
      diffs += 1 if e != b[i]
    end
    diffs
  end
end
