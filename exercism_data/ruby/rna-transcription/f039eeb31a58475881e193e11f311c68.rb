class Complement
  DNA_MAPPING = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_MAPPING = DNA_MAPPING.invert

  class << self
    %w(dna rna).each do |str|
      define_method("of_#{str}") do |string|
        string.chars.map {|char| const_get("#{str.upcase}_MAPPING")[char]}.join
      end
    end
  end
end
