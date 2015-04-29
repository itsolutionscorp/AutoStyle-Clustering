class Hamming

  def self.compute(origin_strand,other_strand)
    i = 0
    counts = 0
    other_strand_arr = other_strand.chars
    origin_strand.chars.each do |x|
      # checking nil for ignores_extra_length_on_original_strand_when_longer
      if other_strand_arr[i].nil?
        break
      elsif other_strand_arr[i] != x
        counts += 1
      end
      i += 1
    end 
    counts
  end

end
