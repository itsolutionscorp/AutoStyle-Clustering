class DNA
  DNA = %W(A T C G)

  def initialize(input)
    @input = input.strip.upcase.split('')
    @dna = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    validate_dna
  end

  def count(nucleotide)
    if @input.empty?
      0
    elsif !DNA.include?(nucleotide)
      raise ArgumentError
    else
      nucleotide_counter(nucleotide)[nucleotide]
    end
  end

  def nucleotide_counts
    if @input.empty? then @dna else nucleotide_counter end
  end

  def nucleotide_counter(nucleotide=nil)
    r = @dna.dup
    @input.each do |i|
      if nucleotide.nil?
        r[i] += 1 if DNA.include?(i)
      else
        r[i] += 1 if DNA.include?(i) && i==nucleotide
      end
    end
    r
  end

  def validate_dna
    @input.each { |n| raise ArgumentError if !DNA.include?(n) }
  end
end
