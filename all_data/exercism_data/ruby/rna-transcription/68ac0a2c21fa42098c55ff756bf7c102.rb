class Complement
  def self.of_dna(seq)
    seq.split('').reduce('') do |sum, elt|
      case elt
      when "C"
        sum << "G"
      when "G"
        sum << "C"
      when "T"
        sum << "A"
      when "A"
        sum << "U"
      end
    end
  end

  def self.of_rna(seq)
    seq.split('').reduce('') do |sum, elt|
      case elt
      when "C"
        sum << "G"
      when "G"
        sum << "C"
      when "U"
        sum << "A"
      when "A"
        sum << "T"
      end
    end
  end
end
