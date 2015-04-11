class DNA

  attr_reader :word

  def initialize(input)
    @word = input
    validate_dna
  end

  def count(letter)
    validate_search(letter)
    word.count(letter)
  end

  def nucleotide_counts
    {"A"=>count('A'), "T"=>count('T'), "C"=>count('C'), "G"=>count('G')}
  end

  def valid_dna_letters
    ["A","T","C","G"]
  end

  def valid_dna_rna_letters
    ["A","T","C","G","U"]
  end

  def validate_dna
    unless word.split("").all?{|letter| valid_dna_letters.include?(letter)}
      raise ArgumentError.new("DNA only please")
    end
  end

  def validate_search(letter)
     unless valid_dna_rna_letters.include?(letter)
      raise ArgumentError.new("#{letter} is not valid")
    end
  end

end
