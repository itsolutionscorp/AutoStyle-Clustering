class Complement
  def self.of_dna dna
    get_complement_of :dna, dna
  end

  def self.of_rna rna
    get_complement_of :rna, rna
  end

  def self.get_complement_of type, input
    input.chars.map do |char|
      send "get_#{type}_translation_for", char
    end.join
  end

  def self.get_rna_translation_for char
    case char
      when 'C' then 'G'
      when 'G' then 'C'
      when 'A' then 'T'
      when 'U' then 'A'
    end
  end

    def self.get_dna_translation_for char
    case char
      when 'C' then 'G'
      when 'G' then 'C'
      when 'T' then 'A'
      when 'A' then 'U'
    end
  end
end
