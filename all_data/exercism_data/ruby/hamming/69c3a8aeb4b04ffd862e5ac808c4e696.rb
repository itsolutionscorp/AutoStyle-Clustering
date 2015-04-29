class Hamming

  def self.compute(string1, string2)
    (0..string1.length).map {|char| :change if string1[char] != string2[char] }
                    .count(:change)
  end

end
