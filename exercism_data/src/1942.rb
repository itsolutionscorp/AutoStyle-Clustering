class Hamming
  def compute(first_strand, second_strand)
    differences = 0
    first_strand.each_char.with_index do |character, index|
      if character != second_strand[index]
        differences += 1
      end
    end

    differences
  end
end
