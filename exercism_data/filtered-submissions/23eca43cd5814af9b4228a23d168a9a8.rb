class Hamming
  def compute(string_1, string_2)
    
    string_1_characters = string_1.chars
    string_2_characters = string_2.chars

    hamming_distance = 0
    
    string_1_characters.zip(string_2_characters).each do |character_1, character_2|
      character_1 == character_2 ? hamming_distance : hamming_distance +=1
    end

    hamming_distance
  end
end
