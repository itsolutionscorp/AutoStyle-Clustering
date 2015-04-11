class Hamming

  def initialize


  end

  def self.compute(first, second)
    first  = first.split(//)                                              # => ["A", "B", "C", "D"]
    second = second.split(//)                                             # => ["A", "G", "F", "A"]
    length = first.length > second.length ? second.length : first.length  # => 4
    difference = 0
    length.times do |index|
      if first[index] != second[index]
        difference += 1
      end
    end
    difference                                                # => 3
  end
end

# Hamming.compute("ABCD", "AGFA")  # => 3
