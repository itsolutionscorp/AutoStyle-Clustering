class Hamming
  def self.compute first, second
    return 0 if first == second
    DNA.new(first).difference(DNA.new(second))
  end
end

class DNA
  def initialize str
    @strand = str
  end

  def difference dna
    score = 0
    strand_values.each_with_index do |v, i|
      break if i >= dna.length
      score = score + 1 unless dna.has_value_at(i, v)
    end
    score
  end

  def length
    strand_values.length
  end

  def has_value_at index, value
    value_at(index) == value
  end

  private

  def value_at index
    strand_values[index]
  end


  def strand_values
    @strand_values ||= @strand.split('')
  end

end
