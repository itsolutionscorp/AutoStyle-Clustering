class Complement

  @DNA_NUCLEOTIDES = [:G, :C, :T, :A]
  @RNA_NUCLEOTIDES = [:C, :G, :A, :U]

  @DNA_COMPLEMENTS = Hash[@DNA_NUCLEOTIDES.zip(@RNA_NUCLEOTIDES)]
  @RNA_COMPLEMENTS = Hash[@RNA_NUCLEOTIDES.zip(@DNA_NUCLEOTIDES)]


  def self.of_rna( strand )
    transpose_nucleotides( string_to_array_of_symbols( strand ), @RNA_COMPLEMENTS)
  end

  def self.of_dna( strand )
    transpose_nucleotides( string_to_array_of_symbols( strand ), @DNA_COMPLEMENTS)
  end

  def self.string_to_array_of_symbols( strand )
    strand.split(//).map(&:to_sym)
  end

  def self.transpose_nucleotides( array, complement_hash )
    result = ""
    array.each do |elem|
      result << complement_hash[elem].to_s
    end

    result
  end
end
