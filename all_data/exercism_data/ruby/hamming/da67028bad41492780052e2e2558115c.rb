class DNA
  def initialize(base_strand)
    @base_strand = base_strand
  end

  def hamming_distance(test_strand)
    mutations = 0
    unless @base_strand.nil? || test_strand.nil? 
      for i in 0..@base_strand.length - 1
        break if i > test_strand.length - 1
        mutations += 1 if @base_strand[i] != test_strand[i]
      end 
    end
    mutations
  end
end
