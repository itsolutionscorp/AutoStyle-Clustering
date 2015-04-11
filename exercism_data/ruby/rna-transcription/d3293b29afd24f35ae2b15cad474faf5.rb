class Complement

  def self.of_dna(strand)
    chars_array = strand.chars
    chars_array.each_index do |i|
      chars_array[i] = complement_for chars_array[i], :dna
    end
    chars_array.join
  end

  def self.of_rna(strand)
    chars_array = strand.chars
    chars_array.each_index do |i|
      chars_array[i] = complement_for chars_array[i], :rna
    end
    chars_array.join
  end

  def self.complement_for(nucleotid, type)
    case nucleotid
    when "C"
      return "G"
    when "G"
      return "C"
    when "T", "U"
      return "A"
    when "A"
      if type == :rna
        return "T"
      else
        return "U"
      end
    else
      return nucleotid
    end
  end

end
