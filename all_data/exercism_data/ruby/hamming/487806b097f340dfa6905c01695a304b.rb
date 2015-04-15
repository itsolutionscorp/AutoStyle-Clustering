class Hamming

  def self.hamming_distance
    if @x_dna.count < @y_dna.count
      @y_dna.pop(@y_dna.count - @x_dna.count)
      @hamming_y_dna = @y_dna
      @hamming_x_dna = @x_dna
    elsif @x_dna.count > @y_dna.count
      @x_dna.pop(@x_dna.count - @y_dna.count)
      @hamming_x_dna = @x_dna
      @hamming_y_dna = @y_dna
    else
      @hamming_x_dna = @x_dna
      @hamming_y_dna = @y_dna
    end
  end

  def self.compute(x_dna, y_dna)
    @mutated_strands = 0
    @x_dna = x_dna.split('')
    @y_dna = y_dna.split('')
    self.hamming_distance
    dna_comparison = @hamming_x_dna.zip(@hamming_y_dna)
      dna_comparison.each do |x, y|
        if x != y then @mutated_strands += 1 end
      end
    @mutated_strands
  end

end
