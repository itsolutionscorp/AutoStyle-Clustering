class Complement

  DNA = {"C" => "G",
         "G" => "C",
         "T" => "A",
         "A" => "U"}

  RNA = {"C" => "G",
         "G" => "C",
         "U" => "A",
         "A" => "T"}

  def self.of_dna(letters)
    match(DNA, letters)
  end

  def self.of_rna(letters)
    match(RNA, letters)
  end

  def self.match(type, letters)
    complements = letters.chars.map do |letter|
      type[letter]
    end
    complements.join
  end
end
