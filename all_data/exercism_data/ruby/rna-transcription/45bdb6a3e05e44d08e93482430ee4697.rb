class Complement

  @common_hash = {"G" => "C", "C" => "G"}

  @c_of_rna = {"U" => "A", "A" => "T"}

  @c_of_dna = {"A" => "U", "T" => "A"}

  def self.convert(acid_array)
    converted_array = []
    acid_array.each do |char|
      unless @common_hash[char] || @specific_hash[char]
        raise ArgumentError.new("not a valid strand")
      end
      converted_array.push(@common_hash[char] || @specific_hash[char])
    end
    converted_array
  end

  def self.main(raw_acid)
    convert(raw_acid.split(//)).join("")
  end

  def self.of_dna(dna)
    @specific_hash = @c_of_dna
    main(dna)
  end

  def self.of_rna(rna)
    @specific_hash = @c_of_rna
    main(rna)
  end
end
