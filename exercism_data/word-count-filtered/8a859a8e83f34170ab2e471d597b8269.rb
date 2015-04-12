class Phrase

  SPLITTER = /[^\w']+/

  def initialize string
    @string = string
  end

  def word_count
    @string.downcase
           .split(SPLITTER)
           .each_with_object(Hash.new(0)) do |word,hash|
              hash[word] += 1
           end
  end

end
