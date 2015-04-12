class Hamming
  def compute(first_strand, second_strand)
    first_strand.split('').each_with_index.inject(0) do |count, (char, index)|
      count += 1 if char != second_strand[index]
      count
    end
  end
end
