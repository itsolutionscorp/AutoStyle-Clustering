class Hamming
  def compute strand1, strand2
    strand1.unpack("C*").zip(strand2.unpack("C*"))
      .reject { |array| array.include? nil }
      .map { |a, b| a ^ b }
      .reject { |x| x == 0 }
      .size
  end
end
