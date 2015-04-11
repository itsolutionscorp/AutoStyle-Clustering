class Complement

  DNA_TO_RNA = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'}

  RNA_TO_DNA = DNA_TO_RNA.invert

  class << self
    def of_dna(dna_str)
      map_str(DNA_TO_RNA, dna_str)
    end

    def of_rna(rna_str)
      map_str(RNA_TO_DNA, rna_str)
    end

  private

    def map_str(mapping, str)
      chars = str.chars
      mapped_chars = chars.map { |char| mapping[char] }

      mapped_chars.join('')
    end
  end
end
