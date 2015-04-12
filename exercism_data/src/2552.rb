class Hamming 
  def compute(source, dest)
    return nil unless source.kind_of?(String) and dest.kind_of?(String) 
    distance = 0
    max_length = source.length > dest.length ? dest.length : source.length
    0.upto(max_length - 1) do |idx|
      distance += 1 if source[idx] != dest[idx]
    end
    distance
  end
end
