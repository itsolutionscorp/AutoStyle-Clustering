class Hamming

  def self.compute(strand_1, strand_2)
    strand_1[0, strand_2.size] if strand_1.size != strand_2.size
    result = []
    strand_1.chars.each_with_index do |char_1, index_1|
      strand_2.chars.each_with_index do |char_2, index_2|
        if index_1 == index_2
          char_1 == char_2 ? result << " " : result << "^"
        end
      end
    end
    result.join.scan(/\^/).count
  end

end
