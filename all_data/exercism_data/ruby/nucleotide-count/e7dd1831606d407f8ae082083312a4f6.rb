class DNA
  def initialize(input)
    @default_dna = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @input = input.strip.upcase.split('')
    raise_error
  end

  def raise_error
    @input.each do |n|
      raise ArgumentError if !@default_dna.has_key?(n) || n=='U'
    end
  end

  def count(nucleotide)
    if @input.empty? || (nucleotide=='U')
      0
    elsif !@default_dna.has_key?(nucleotide)
      raise ArgumentError
    else
      nucleotide_times(nucleotide)[nucleotide]
    end
  end

  def nucleotide_counts
    if @input.empty?
      @default_dna
    else
      nucleotide_times
    end
  end

  def nucleotide_times(nucleotide = nil)
    dna_collection = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @input.each do |n|
      if n.nil?
        dna_collection[n] += 1 if dna_collection.has_key?(n)
      else      
        dna_collection[n] += 1 if dna_collection.has_key?(n) && n == n
      end
    end
    dna_collection
  end
  
end
