class Hamming  
  
  def self.compute(string1, string2)
    result = []
    char_index = 0
    string1.each_char do |character|
      result << 1 if character != string2[char_index]
      char_index += 1      
    end
    result.inject(:+) ? result.inject(:+) : 0
  end
  
  # def self.convert_to_hash(arg)
  #   hash_convsersion = {}
  #   arg.split("").each_with_index do |el, index|
  #     hash_convsersion[index] = el
  #   end
  #   hash_convsersion
  # end
  #
  # def self.compare_hashes(hash1, hash2)
  #   hash1.reject! do |key, value|
  #     value == hash2.fetch(key)
  #   end
  #   hash1.length
  # end
end
