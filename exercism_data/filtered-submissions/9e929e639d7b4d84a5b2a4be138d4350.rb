class Hamming
  def compute(strand1, strand2)
    hamming_count = 0
    strand1.split('').each_with_index do |strand, i|
      hamming_count += 1 if strand != strand2.split('')[i]
      break if i == strand2.length - 1 || i == strand1.length - 1
    end
    hamming_count
  end
end
