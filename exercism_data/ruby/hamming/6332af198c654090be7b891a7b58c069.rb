class Hamming
  def self.compute(*args)
    new.compute(*args)
  end

  def compute(string1, string2)
    [string1.size, string2.size].min.times.inject(0) do |distance, character_index|
      string1[character_index] == string2[character_index] ? distance : distance + 1
    end
  end
end
