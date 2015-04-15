class Complement

  NUCLEOTIDE_KEY = [['G', 'C'], ['C', 'G'], ['T', 'A'], ['A', 'U']]
  DNA_MAP = Hash[NUCLEOTIDE_KEY]
  RNA_MAP = Hash[NUCLEOTIDE_KEY.map { |pair| pair.reverse }]

  def initialize(type, strand)
    @type = type
    @strand = prepare_strand(strand)
    check_for_validity
  end

  def self.of_dna(strand)
    new('DNA', strand).find_complement
  end

  def self.of_rna(strand)
    new('RNA', strand).find_complement
  end

  def find_complement
    complment_array = @strand.map do |nucleotide|
      complement_map[nucleotide]
    end
    complment_array.join('')
  end

  private

  def prepare_strand(strand)
    character_array = strand.split('')
  end

  def complement_map
    eval "#{@type}_MAP"
  end

  def check_for_validity
    @strand.each do |nucleotide|
       raise ArgumentError.new("#{@type} cannot contain #{nucleotide}") unless complement_map.has_key?(nucleotide)
    end
  end
end
