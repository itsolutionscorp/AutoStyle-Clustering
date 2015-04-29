class Complement
  def self.initialize
    @rna_group = {1 => "G", 2 => "T", 3 => "A"}
    @dna_group = {1 => "C", 2 => "A", 3 => "U"}
  end

  def self.of_dna(rna)
    result = ""

    initialize

    rna.each_char do |char|
      rna_index = find_rna_index(char)
      dna_index = find_dna_index(char)

      if rna_index
        result << @dna_group[rna_index]
      elsif dna_index
        result << @rna_group[dna_index]
      end
    end
    result
  end

  def self.of_rna(dna)
    result = ""

    initialize

    dna.each_char do |char|
      dna_index = find_dna_index(char)
      rna_index = find_rna_index(char)

      if dna_index
        result << @rna_group[dna_index]
      elsif rna_index
        result << @dna_group[rna_index]
      end
    end
    result
  end

  private

  def self.find_dna_index(character)
    @dna_group.select { |_, letter| letter == character }.keys[0]
  end

  def self.find_rna_index(character)
    @rna_group.select { |_, letter| letter == character }.keys[0]
  end
end
