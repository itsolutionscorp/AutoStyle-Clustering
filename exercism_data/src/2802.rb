def compute(strand1, strand2)
    difference = 0

    strand1_letters = strand1.chars.to_a
    strand2_letters = strand2.chars.to_a

    strand1_letters.each_with_index do |letter, index|
      if strand2_letters[index] != nil then
        difference += 1 if letter != strand2_letters[index]
      end
    end

    difference
  end