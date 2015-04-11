class Grains

  NUMBER_OF_SQUARES = 64
  
  private_constant :NUMBER_OF_SQUARES

  def square(index)
    2 ** (index - 1)
  end

  def total
    Enumerator.new do |yielder|
      index = 1
      loop do
        yielder.yield square(index)
        index += 1
      end
    end.first(NUMBER_OF_SQUARES).inject(:+)
  end

end
