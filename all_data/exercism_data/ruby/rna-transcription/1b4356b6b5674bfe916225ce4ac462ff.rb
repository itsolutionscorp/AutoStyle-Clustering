#
# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
require 'pry'
class Complement
    TO_RNA = {
                'G' => 'C',
                'C' => 'G',
                'T' => 'A',
                'A' => 'U'
              }

  def self.of_dna(input)
    if input == 'U'
      raise ArgumentError
    else
      input.chars.map{|char| TO_RNA[char]}.join
    end
  end

  def self.of_rna(input)
    if input == 'T'
      raise ArgumentError
    else
      input.chars.map{|char| TO_RNA.invert[char]}.join
    end
  end
end
