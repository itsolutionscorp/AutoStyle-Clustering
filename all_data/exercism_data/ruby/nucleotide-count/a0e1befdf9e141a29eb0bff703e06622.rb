class DNA
  def initialize(nucleotides)
    @nucleotides = nucleotides.split( '' )
    raise ArgumentError if @nucleotides.include? ( 'U' )
    @nucleotides.each { |nucleotide| check_molecule( nucleotide ) }
  end

  def count( nucleotide )
    check_molecule( nucleotide )
    @nucleotides.count( nucleotide )
  end

  def check_molecule( nucleotide )
    molecules = ['A', 'T', 'C', 'G', 'U']
    raise ArgumentError unless molecules.include?( nucleotide )
  end

  def nucleotide_counts
    counts = {}
    counts['A'] = @nucleotides.count( 'A' ) 
    counts['T'] = @nucleotides.count( 'T' ) 
    counts['C'] = @nucleotides.count( 'C' ) 
    counts['G'] = @nucleotides.count( 'G' ) 
    counts
  end
end
