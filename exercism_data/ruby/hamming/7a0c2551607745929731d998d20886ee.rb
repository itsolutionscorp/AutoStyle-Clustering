class Hamming
  class << self
    def compute(first_strand, second_strand)
      distance = 0
      split(first_strand).each_with_index do |segment, i|
        distance+=1 unless segment.include?(split(second_strand)[i])
      end
      distance
    end

    def split(strand)
      strand.split(//)
    end
  end
end
