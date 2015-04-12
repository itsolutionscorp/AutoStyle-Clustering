class Hamming

  def compute(*strands)
    short, long = strands.sort_by(&:length)

    difference = 0
    short.split('').each_with_index do |character, index|
      if character != long[index]
        difference += 1
      end
    end
    difference
  end
end
