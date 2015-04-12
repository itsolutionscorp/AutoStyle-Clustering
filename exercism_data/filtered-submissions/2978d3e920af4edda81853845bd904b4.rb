class Hamming
  def compute(first,second)
    len = first.length < second.length ? first.length : second.length
    (0..(len-1)).map { |i|
      first[i] == second[i] ? 0 : 1
    }.reduce(0) {|memo,item| memo += item}
  end
end
