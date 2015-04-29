module Hamming
  def self.compute(one,two)
    other = two.chars
    one.chars.each_with_index.inject(0) do |acc,(char,i)|
      acc += 1 unless char==other[i]
      acc
    end
  end
end
