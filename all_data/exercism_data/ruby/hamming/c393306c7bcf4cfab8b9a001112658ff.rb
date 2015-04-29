class DNA
  attr_reader :strand
  
  def initialize(strand)
    @strand=strand
  end

  def hamming_distance(hamming_strand)
    hamming_count(hamming_strand)
  end

  private
  
  def hamming_count(hamming_strand)
    strand_array=prepare_array(strand)
    hamming_array=prepare_array(hamming_strand)

    short, long = [strand_array, hamming_array].sort_by &:length
    count = (0..short.length-1).inject(0) {|count, i| short[i] == long[i] ? count : count+=1}
  end

  def prepare_array(string)
    string.split("")
  end
end
