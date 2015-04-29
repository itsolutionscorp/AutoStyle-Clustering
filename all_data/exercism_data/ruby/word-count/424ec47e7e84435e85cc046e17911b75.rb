class Document < String
  def remove_special_characters
    gsub(",", " ").gsub(/[^\w\d\s]*/, "")
  end
end

class Phrase
  def initialize(raw_document)
    @document = Document.new(raw_document)
  end

  def word_count
    unless @word_list
      @word_list = Hash.new(0)
      words = @document.remove_special_characters.downcase.split(" ")
      words.each do |word|
        @word_list[word] += 1
      end
    end
    @word_list
  end
end
