class Hamming
  def compute(strand1, strand2)
    diff_count = 0

    strand1.each_char.with_index do |char1, i|
      char2 = strand2[i]
      break if char2.nil?

      diff_count += 1 if char1 != char2
    end

    diff_count
  end
end
