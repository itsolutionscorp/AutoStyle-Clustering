class StringMapper
  
  attr_reader :string
  
  def initialize string
    @string = string
  end
  
  def zero_hash
    @zero_hash = Hash[@string.chars.map{|c| [c, 0] }]
  end
  
  def nil_hash
    nil_hash = (@zero_hash || zero_hash).clone
    nil_hash.each_key {|k| nil_hash[k] = nil }
    
    return nil_hash
  end
end

class DNA
  
  attr_reader :sequence
  
  dna_constants = StringMapper.new "ATCG"
  
  ZERO_OCCURRENCES = dna_constants.zero_hash.clone
  NIL_OCCURRENCES = dna_constants.nil_hash.clone
  KEYS             = dna_constants.string
  
  def initialize sequence
    @sequence = sequence.gsub ' ', '' 
    @occurrences = NIL_OCCURRENCES.clone
    
    raise( ArgumentError ) if  @sequence =~ /[^#{KEYS}]+/
    
  end
  def count nucleotide
    return 0 if nucleotide=='U'
    return 0 if empty_sequence?
    if KEYS.include?(nucleotide) 
      @occurrences[nucleotide] ||= @sequence.chars.count{|x| x==nucleotide}
    else
      raise( ArgumentError )
    end
  end
  def nucleotide_counts 
    return @occurrences = ZERO_OCCURRENCES  if empty_sequence?
    
    @occurrences.keys.each do |nucleotide|
      @occurrences[nucleotide] ||= count nucleotide
    end
    @occurrences
  end
  
  def empty_sequence?
    @sequence.empty?
  end

end
