class String
  def anagram?(word)
    return false if word.downcase == self.downcase
    return self.downcase.to_hash == word.downcase.to_hash    
  end

  def to_hash
    self.split('').each_with_object(Hash.new(0)) { |letter, hash| hash[letter] += 1 }      
  end    
end

class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select { |x| @word.anagram?(x) }
  end
end
