class Complement

  def self.of_dna dna
    map_sequence dna, DNA_TO_RNA, 'U'
  end

  def self.of_rna rna
    map_sequence rna, RNA_TO_DNA, 'T'
  end

  private

  DNA_TO_RNA = { G: 'C', C: 'G', T: 'A', A: 'U' }
  RNA_TO_DNA = { G: 'C', C: 'G', A: 'T', U: 'A' }

  def self.map_sequence original_sequence, hash, error_char
    raise ArgumentError if string.include? error_char
    string.chars.each.map { |c| hash[c.to_sym] }.join
  end
end
