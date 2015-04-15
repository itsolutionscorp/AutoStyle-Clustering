class Complement
  def initialize(params)
    @type = :dna if params[:dna]
    @type = :rna if params[:rna]
    @string = params[@type]
  end

  def dna?
    @type == :dna
  end

  def rna?
    @type == :rna
  end

  def dna_to_rna_map
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def nucleotides(str)
    str.split('')
  end

  def rna_complement_for(nucleotide)
    dna_to_rna_map[nucleotide] or raise ArgumentError, "Invalid DNA nucleotide '#{nucleotide}'"
  end

  def dna_complement_for(nucleotide)
    dna_to_rna_map.key nucleotide or raise ArgumentError, "Invalid RNA nucleotide '#{nucleotide}'"
  end

  def run
    hammer = lambda { |nucleotide| rna_complement_for(nucleotide) } if dna?
    hammer = lambda { |nucleotide| dna_complement_for(nucleotide) } if rna?

    nucleotides(@string).map(&hammer).join
  end

  def self.of_dna(dna)
    Complement.new({ :dna => dna }).run
  end

  def self.of_rna(rna)
    Complement.new({ :rna => rna }).run
  end
end
