class Hamming
  def compute(string_1, string_2)

    hamming_distance = 0
    
    string_1.chars.zip(string_2.chars).each do |character_1, character_2|
      character_1 == character_2 ? hamming_distance : hamming_distance +=1
    end

    hamming_distance
  end
end
