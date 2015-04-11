class Anagram

  def initialize word
    @word = word
  end

  def match test_words
    test_words.delete_if { |word| word.length != @word.length || @word =~ /#{word}/i }
              .select do |word|
      character_hash(@word.downcase) == character_hash(word.downcase)
    end
  end

  def character_hash string
    string.each_char.with_object(Hash.new 0) do |chr,hash|
      hash[chr] +=1
    end
  end

end
