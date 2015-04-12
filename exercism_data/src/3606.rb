class Hamming
  attr_reader :counter

  def compute(strand1, strand2)
    @counter = 0
    one = strand1.split('')
    two = strand2.split('')

    one.take(two.length).each_with_index do |_, index|
      @counter += 1 unless one[index] == two[index]
    end

    return @counter
  end

end
