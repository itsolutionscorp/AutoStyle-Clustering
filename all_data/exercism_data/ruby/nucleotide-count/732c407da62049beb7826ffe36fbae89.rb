class DNA
  
  attr_reader :sequence
  
  ZERO_OCCURRENCES = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  KEYS             = "ATCG"
  def initialize sequence
    @sequence=sequence.gsub ' ', ''
    @occurrences = {'A' => nil, 'T' => nil, 'C' => nil, 'G' => nil}
    
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
