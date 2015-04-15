class DNA
  def initialize(dna, nucleotide=Nucleotide.new)
    @dna = dna
    @nucleotide = nucleotide
  end

  def count(nucleotide)
    ensure_valid_nucleotide nucleotide
    nucleotide_counts.fetch(nucleotide) { 0 }
  end

  def nucleotide_counts
    @nucleotide.calculate_freqs(freq_map)
  end

  private

  def freq_map
    @freq_map ||= @dna.chars.each_with_object(Hash.new(0)) do |letter, freq|
      freq[letter] += 1
    end
  end

  def ensure_valid_nucleotide(nucleotide)
    raise ArgumentError, "#{nucleotide} is not a valid nucleotide" unless @nucleotide.valid? nucleotide
  end
end

class Nucleotide
  def calculate_freqs(nucleotide_map)
    freqs.merge(nucleotide_map) do |key, old_val, new_val|
      old_val + new_val
    end
  end

  def valid?(nucleotide)
    (freqs.keys + ['U']).include? nucleotide
  end

  private

  def freqs
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end
end
