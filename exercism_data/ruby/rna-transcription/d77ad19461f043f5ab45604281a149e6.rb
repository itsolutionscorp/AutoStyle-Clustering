class Complement

  def self.of_dna dna_string
    array1 = ['G', 'C', 'T', 'A']
    array2 = ['C', 'G', 'A', 'U']

    rna_string = ""
    dna_string.length.times do |i|
      index = array1.index(dna_string[i])
      rna_string += array2[index]
    end
    rna_string
  end

  def self.of_rna rna_string
    array1 = ['G', 'C', 'T', 'A']
    array2 = ['C', 'G', 'A', 'U']

    dna_string = ""
    rna_string.length.times do |i|
      index = array2.index(rna_string[i])
      dna_string += array1[index]
    end
    dna_string
  end

end
