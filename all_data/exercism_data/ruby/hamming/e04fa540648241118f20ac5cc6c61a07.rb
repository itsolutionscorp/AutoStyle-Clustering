class Hamming  
  
  def self.compute(first, second)
    first_hash = self.convert_to_hash(first)
    second_hash = self.convert_to_hash(second)
    self.compare_hashes(first_hash,second_hash)
  end
  
  def self.convert_to_hash(arg)
    hash_convsersion = {}
    arg.split("").each_with_index do |el, index|
      hash_convsersion[index] = el
    end
    hash_convsersion
  end
  
  def self.compare_hashes(hash1, hash2)
    hash1.reject! do |key, value|
      value == hash2.fetch(key)
    end
    hash1.length
  end  
end
