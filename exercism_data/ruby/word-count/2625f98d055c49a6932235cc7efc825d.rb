class Document < String
  def words
    scan(/\w+/)
  end
end

class Phrase
  def initialize(raw_document)
    @document = Document.new(raw_document)
  end

  def word_count
    @document.downcase.words.each_with_object(Hash.new(0)) do |word, word_list|
      word_list[word] += 1
    end
  end
end
