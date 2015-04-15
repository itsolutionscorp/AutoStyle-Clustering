class Phrase < Struct.new(:phrase)
  WORD_REGEXP = /[\w']+/

  def word_count
    phrase.scan(WORD_REGEXP).each_with_object(Hash.new(0)) do |word, frequencies|
      frequencies[word.downcase] += 1
    end
  end
end
