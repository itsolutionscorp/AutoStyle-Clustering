class Hamming

  def compute(a, b)
    @count = 0
    first_strand  = a.split(//)
    second_strand = b.split(//)
    first_strand.each_with_index do |letter, i|
      if letter != second_strand[i] && second_strand[i] != nil
        @count += 1
      end
    end
    @count
  end

end
