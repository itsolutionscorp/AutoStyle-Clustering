class DNA
  def initialize(initial_strand)
    @initial_strand = initial_strand
  end

  def hamming_distance(strand)
    distance = 0
    @initial_strand.split(//).each_index do |index|
      if strand[index] == nil
        break
      else
        if @initial_strand[index] == strand[index]
        else
          distance += 1
        end
      end
    end
    distance
  end
end
