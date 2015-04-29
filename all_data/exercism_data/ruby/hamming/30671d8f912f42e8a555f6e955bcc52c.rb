class Hamming

  attr_accessor :small_strand, :large_strand

  def self.compute(strand1, strand2)
    @small_strand = strand1
    @large_strand = strand2

    determine_size

    count_mutations(strand_array(@small_strand), strand_array(@large_strand))
  end

  def self.strand_array(strand)
    strand.split(//)
  end

  def self.determine_size
    small_strand = @small_strand
    large_strand = @large_strand

    if strand_array(@small_strand).count > strand_array(@large_strand).count
      @small_strand = large_strand
      @large_strand = small_strand
    end
  end

  def self.count_mutations(small_strand, large_strand)
    mutation_count = 0

    small_strand.each_with_index { |mutation, index| mutation == large_strand[index] ? 0 : mutation_count += 1 }

    mutation_count
  end
end
