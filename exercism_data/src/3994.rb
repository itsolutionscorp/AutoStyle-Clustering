class Hamming
  def compute(one, two)
    one.split('').each_with_index.inject(0) do |sum, (letter, index)|
      return sum unless two[index]
      sum += letter == two[index] ? 0 : 1
    end
  end
end
