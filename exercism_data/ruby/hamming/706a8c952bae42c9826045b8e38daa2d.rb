class Hamming
  def self.compute(string1,string2)
    counter = 0
    string1.chars
          .each_index {|i| counter += hamming_number(string1[i],string2[i]) }
    counter
  end

  def self.hamming_number(a,b)
    a == b ? 0 : 1
  end
end
