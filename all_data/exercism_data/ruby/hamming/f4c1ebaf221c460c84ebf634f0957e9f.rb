class Hamming
  def self.compute(string1, string2)
    num_differences = 0

    shortest_length = [string1.length, string2.length].min
    string1 = string1[0...shortest_length]
    string2 = string2[0...shortest_length]

    shortest_length.times do |index|
      letter1 = string1[index]
      letter2 = string2[index]
      if letter1 != letter2
        num_differences += 1
      end
    end

    num_differences
  end
end
