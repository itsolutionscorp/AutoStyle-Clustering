class Hamming
  def self.compute(string_one, string_two)

    string_one.chars.zip(string_two.chars).count do |duo|
      duo[0] && duo[1] && duo[0] != duo[1]
    end

  end
end
