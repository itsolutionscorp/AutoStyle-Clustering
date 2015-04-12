class Hamming

  def compute(from, to)
    return 0 if from.length != to.length
    from.chars.zip(to.chars).select{ |e| e[0] != e[1] }.count
  end
end
