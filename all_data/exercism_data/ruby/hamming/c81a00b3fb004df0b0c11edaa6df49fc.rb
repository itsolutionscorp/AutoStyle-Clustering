class Hamming

  def self.compute(str1, str2)
    dna_one = create_array(str1)
    dna_two = create_array(str2)
    diff = 0

    dna_one.each_with_index do |value, index|
      diff += 1 if dna_two[index] != value
    end

    diff
  end

  private

  def self.create_array(str)
    str.split("")
  end

end
