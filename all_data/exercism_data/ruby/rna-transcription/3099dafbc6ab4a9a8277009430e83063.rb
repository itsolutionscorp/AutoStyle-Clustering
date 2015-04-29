class Complement



  def self.of_dna(dna)
    self.new().of_dna(dna)
  end

  def self.of_rna(rna)
    self.new().of_rna(rna)
  end

  def of_dna(dna)
    dna_to_rna_complement_map = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    complement(dna, dna_to_rna_complement_map);
  end

  def of_rna(rna)
    rna_to_dna_complement_map = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }

    complement(rna, rna_to_dna_complement_map);
  end

  private
  def complement(target, map)
    copy = target

    target.each_char.with_index do |char, index|
      map.each do |pattern, replacement|
        if char.match(pattern)
          char = char.gsub(pattern, replacement)
          break
        end
      end
      copy[index] = char
    end

    copy
  end
end
