class Hamming

  def initialize(first, second)

  end

  def self.compute(first, second)
    counter = 0
    first_array = first.split('')
    if first_array.size > second.size
      difference = first_array.size - second.size
      first_array.pop(difference)
    end
    first_array.each_with_index do |gene, index|
      if gene != second[index]
        counter += 1
      end
    end
    counter
  end

end
