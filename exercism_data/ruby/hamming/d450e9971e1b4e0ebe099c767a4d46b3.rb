class Hamming
  def self.compute(first_strand, second_strand)
    ordered_strands = [first_strand, second_strand].sort_by { |s| s.length }

    ordered_strands[0].chars.each_with_index.select { |c,i| c != ordered_strands[1].chars[i] }.count
  end
end
