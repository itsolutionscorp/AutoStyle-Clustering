class Hamming
  def compute(a, b)
    @count = 0
    for index in 0...(a.chars.length > b.chars.length ? b.chars.length : a.chars.length) do
      if a.chars[index] != b.chars[index]
        @count += 1
      end 
    end
    return @count
  end
end
