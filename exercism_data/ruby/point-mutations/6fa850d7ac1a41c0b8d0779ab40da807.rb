class DNA
  attr_reader :strand
  def initialize strand
    @strand = strand.to_s.upcase
  end
  
  def hamming_distance mutated_strand
    mutation = DNA.new mutated_strand
    length_limit = mutation.strand.length < strand.length ?  mutation.strand.length : strand.length
    mutation_enum = mutation.strand.chars[0...length_limit].each
    strand_enum = strand.chars[0...length_limit].each
    strand_enum.reject {|base| mutation_enum.next == base }.length
  end
end
