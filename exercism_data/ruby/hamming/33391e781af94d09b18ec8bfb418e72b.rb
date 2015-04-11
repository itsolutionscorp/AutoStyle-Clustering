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
    count=0
    strand_array=prepare_array(strand)
    hamming_array=prepare_array(hamming_strand)

    #this part is UGLY! has to be a more ruby way of doing this...
    if strand_array.length <= hamming_array.length
      strand_array.each_with_index do |nucleotide,index|
      count += 1 if nucleotide != hamming_array[index]
      end
    else
      hamming_array.each_with_index do |nucleotide,index|
      count +=1 if nucleotide != strand_array[index]
      end
    end
    count
    #end ugliness
  end

  def prepare_array(string)
    string.split("")
  end
  
end
