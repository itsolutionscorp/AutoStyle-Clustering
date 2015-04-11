class Complement
  DNA = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  RNA = {
    "G" => "C",
    "C" => "G",
    "U" => "A",
    "A" => "T"
  }

  def self.of_dna(strand)
    find_complement(DNA, strand)
  end

  def self.of_rna(strand)
    find_complement(RNA, strand)
  end
  
  private
    def self.find_complement(set, strand)
      strand.each_char.with_object("") { |char, str| str << set[char] }
    end
end
