class Complement
  # using a hash map to do lookups instead of using branching logic
  DNA_TO_RNA_MAP = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }
  RNA_TO_DNA_MAP = DNA_TO_RNA_MAP.invert

  def self.of_dna(strand)
    strand.chars.map do |entry|
      DNA_TO_RNA_MAP.fetch(entry)
    end.join("")
  end

  def self.of_rna(strand)
    strand.chars.map do |entry|
      RNA_TO_DNA_MAP.fetch(entry)
    end.join("")
  end
end
