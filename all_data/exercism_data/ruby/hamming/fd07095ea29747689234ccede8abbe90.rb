class Hamming
  attr_reader :a, :b

  def self.compute(a, b)
    if a == b
      0
    else
      compare_string(a,b)
    end
  end

  def self.compare_string(string1, string2)
    min_length = [string1.length, string2.length].min
    counter = 0
    min_length.times do |i|
      counter += 1 if string1[i] != string2[i]
    end
    counter
  end
end
