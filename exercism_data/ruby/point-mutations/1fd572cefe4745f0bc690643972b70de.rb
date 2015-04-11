class DNA

  attr_reader :strand
  
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(compared_strand)
    compared = nucleotides(compared_strand)
    original = nucleotides(strand)
    measure_mutations(original, compared)
  end

  def measure_mutations(original, compared)
    original.each.with_index.inject(0) do |sum, (nucleo, index)|
      unless duplicates?(strand[index], compared[index]) || compared[index].nil?
        sum += 1
      else
        sum
      end
    end
  end

  def duplicates?(nucleo_1, nucleo_2)
    nucleo_1 == nucleo_2 
  end

  def nucleotides(sequence)
    sequence.split('')
  end
end
