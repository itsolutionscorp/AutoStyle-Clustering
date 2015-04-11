class Hamming

  def self.compute(a, b)
    first, second = a.chars, b.chars
    difference = 0

    first.each_with_index do |c, index|
      if c != second[index]
        difference += 1 unless second[index].nil?
      end
    end

    return difference
  end
end
