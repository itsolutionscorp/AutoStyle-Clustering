require 'ostruct'

class Hamming
  def compute(strand_1, strand_2)
    strand_diff = OpenStruct.new(count: 0)
    strand_diff.tap do |diff|
      strand_1.split(//).each_with_index do |letter, index|
        diff.count += 1 if strand_1[index] != strand_2[index]
      end
    end
    strand_diff.count
  end
end
