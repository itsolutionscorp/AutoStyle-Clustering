class Hamming
  def self.compute(a, b)
    return 0 if a == b
    a.chars.zip(b.chars).inject(0) do |sum, arr|
      sum+=1 if arr[0] != arr[1]
      sum
    end
  end
end
