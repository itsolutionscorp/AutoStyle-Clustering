class Hamming

  #compute Hamming Distance between two strands
  def compute(first_strand, second_strand)
    first_strand.chars.zip(second_strand.chars).select do 
      |first_base, second_base|
      first_base!=second_base
    end.count
  end

end
