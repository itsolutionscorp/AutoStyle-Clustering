class Complement
  MAPPINGS = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(str)
    str.chars.map do |char|
      MAPPINGS.each do |key, value|
        break value if key == char
      end
    end.join
  end

  def self.of_rna(str)
    str.chars.map do |char|
      MAPPINGS.each do |key, value|
        break key if value == char
      end
    end.join
  end
end
