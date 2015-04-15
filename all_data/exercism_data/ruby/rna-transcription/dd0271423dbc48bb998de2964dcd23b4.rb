class Complement

  CONVERSION = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(strand)
    chars_array = strand.chars
    chars_array.each_index do |i|
      chars_array[i] = complement_for_dna chars_array[i]
    end
    chars_array.join
  end

  def self.of_rna(strand)
    chars_array = strand.chars
    chars_array.each_index do |i|
      chars_array[i] = complement_for_rna chars_array[i]
    end
    chars_array.join
  end

  def self.complement_for_dna(nucleotid)
    if CONVERSION[nucleotid]
      CONVERSION[nucleotid]
    else
      nucleotid
    end
  end

  def self.complement_for_rna(nucleotid)
    if CONVERSION.key(nucleotid)
      CONVERSION.key(nucleotid)
    else
      nucleotid
    end
  end

end
