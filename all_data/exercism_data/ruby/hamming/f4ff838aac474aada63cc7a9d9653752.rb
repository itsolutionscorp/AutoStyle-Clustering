# class Hamming
#   attr_reader :from, :to
#   def initialize(from, to)
#     @from = from
#     @to = to
#   end

#   def self.compute(from, to)
#     new(from, to).compute
#   end

#   def compute
#     split_strands
#       .collect{|n1, n2| individual_distance(n1, n2) }
#       .inject(:+)
#   end

#   def individual_distance(n1, n2)
#     n1 == n2 ? 0 : 1
#   end

#   def split_strands
#     from.chars.zip(to.chars)
#   end
# end

class Hamming
  def self.compute(strand_a, strand_b)
    strand_a = strand_a.split('')
    strand_b = strand_b.split('')

    0 if strand_a == strand_b

    if strand_a.length == strand_b.length
      distance = 0
      strand_a.each_with_index do |nucleotide, index|
        distance += 1 unless nucleotide == strand_b[index]
      end
      distance
    end
  end
end
