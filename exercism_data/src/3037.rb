class Hamming
  def compute strand1, strand2
    (0...[strand1.length, strand2.length].min).each_with_index.count do |item, index|
      strand1[index] != strand2[index]
    end
  end
end
