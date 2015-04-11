class Hamming

  def self.compute(string1, string2)
    sum = 0
    string1.length < string2.length ? shorter = string1 : shorter = string2
    (0...shorter.length).each do |count|
      sum += 1 if string1[count].eql?(string2[count]) == false
    end
    sum
  end

end
