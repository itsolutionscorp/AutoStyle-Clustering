class Hamming

  def compute(first, second)
    first = first.split('')
    second = second.split('')
    result = 0
    i = 0
    first.each do |letter|
      result += 1 unless letter == second[i]
      i += 1
    end
    result
  end
end
