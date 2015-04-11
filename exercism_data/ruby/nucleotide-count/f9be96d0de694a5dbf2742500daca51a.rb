class DNA

  def initialize(letter)
    @letter = letter
  end

  def count(letter)
    if letter == "X"
        raise ArgumentError
    end
    string_size = @letter.count(letter)
    return string_size
  end

  def nucleotide_counts
    hashes = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    hashes['A'] = @letter.count("A")
    hashes['T'] = @letter.count("T")
    hashes['C'] = @letter.count("C")
    hashes['G'] = @letter.count("G")
    return hashes
  end
end
