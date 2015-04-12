class Hamming
  def compute first, second
    trimmed_array = first.split('').take(second.length)
    trimmed_array.each_with_index.reject do |letter, index|
      second[index] == letter
    end.count
  end
end
