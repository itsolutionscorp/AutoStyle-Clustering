class Hamming

  def self.compute(first_DNA, second_DNA)
    counter = 0
    first_DNA.split("").zip(second_DNA.split("")).each do |first, second|
      if (first != second && first != nil && second != nil)
        counter += 1
      end
    end
    return counter
  end

end
