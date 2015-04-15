class Hamming
  def self.compute(string1, string2)
    combinations = string1.chars.zip(string2.chars)

    combinations.reject! {|letters| letters.include?(nil) }

    compare(combinations)
  end

  def self.compare(combinations)
    combinations.count do |letter|
      letter[0] != letter[1]
    end
  end
end
