class Hamming

  def self.compute(strand_one, strand_two)
    strand_one_nucleotides = strand_one.chars
    strand_two_nucleotides = strand_two.chars

    paired_strands = strand_one_nucleotides.zip(strand_two_nucleotides)

    mutations = paired_strands.find_all do |pair|
      mutation?(pair)
    end

    mutations.length
  end

  def self.mutation?(pair)
    if pair.first.nil? || pair.last.nil?
      return false
    elsif pair.first != pair.last
      true
    else
      false
    end
  end
end
