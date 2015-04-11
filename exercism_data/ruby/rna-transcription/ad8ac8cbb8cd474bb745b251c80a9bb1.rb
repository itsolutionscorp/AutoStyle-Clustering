class Complement
  COMPLEMENT = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna string
    map_chars(string) { |char| COMPLEMENT[char] }
  end

  def self.of_rna string
    map_chars(string) { |char| COMPLEMENT.key(char) }
  end


  private

  def self.map_chars string
    string.each_char.map do |char|
      yield char
    end.join
  end
end
