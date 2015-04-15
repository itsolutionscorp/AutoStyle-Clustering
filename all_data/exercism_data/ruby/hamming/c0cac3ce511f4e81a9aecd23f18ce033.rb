class Hamming
  def self.compute(strand1, strand2)
    strand1_letters = strand1.split("")
    strand2_letters = strand2.split("")
    count_differences(strand1_letters, strand2_letters)
  end

  def self.count_differences(strand1_letters, strand2_letters)
    count = 0
    strand1_letters.each_with_index do |letter, index|
      if letter != strand2_letters[index]
        count += 1
      end
    end
    count
  end
end
