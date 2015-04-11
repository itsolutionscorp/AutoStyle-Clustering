class Hamming

  def self.set_up(input_A, input_B)
    @array_A = input_A.chars
    @array_B = input_B.chars
    @count = 0
    @cursor = 0
    
    if array_A.length < array_B.length
      @size = array_A.length
    else
      @size = array_B.length
    end
  end

  def self.compute(input_A, input_B)
    
    while @cursor < @size
      compare_single_input(@array_A[@cursor], @array_B[@cursor])
      @cursor += 1
    end
    
    @count
  end

  def self.compare_single_input(input_A, input_B)
    if input_B == input_A
      return @count
    else
      @count += 1
    end
  end
end
