class Complement
    def self.of_dna(input)
        input_array = input.split("")
        char_map = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

        input_array.map { |char| char_map[char] }.join("")
    end

    def self.of_rna(input)
        input_array = input.split("")
        char_map = {"G" => "C", "C" => "G", "A" => "T", "U" => "A"}

        input_array.map { |char| char_map[char] }.join("")
    end
end
