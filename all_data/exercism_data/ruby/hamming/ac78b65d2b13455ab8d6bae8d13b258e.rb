class Hamming
  def self.compute(first,second)
    (0..(first.length-1)).map { |i|
      first[i] == second[i] ? 0 : 1
    }.reduce(0) {|memo,item| memo += item}
  end
end
