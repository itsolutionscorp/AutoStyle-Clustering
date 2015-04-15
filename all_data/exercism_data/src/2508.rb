def compute first_strand, second_strand
    first_strand.chars.zip(second_strand.chars).count do |strand_position|
      strand_position[0] != strand_position[1]
    end
  end