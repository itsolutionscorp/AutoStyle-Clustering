def compute(input_strand, calculated_strand)
    input_strand_characters = input_strand.scan(/./)
    calculated_strand_characters = calculated_strand.scan(/./)
    count = 0
    [input_strand_characters.length, calculated_strand_characters.length].min.times do |index|
      count += 1 if input_strand_characters[index] != calculated_strand_characters[index]
    end
    return count
  end