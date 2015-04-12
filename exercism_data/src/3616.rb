class Hamming
  def compute(strand1, strand2)
    strand1.empty? && strand2.empty? ? 0 : strand1.split('').each_with_index.map { |letter,i|
        strand2.split('')[i].nil? || letter == strand2[i] ? 0 : 1
      }.inject{|sum, x| sum + x}
  end
end
