class Phrase
  def initialize(phrase)
    #lower case and remove none word characters
    @normalized_phrase = phrase.downcase.gsub(/[^a-zA-Z0-9-'\s]+/, " ")
  end

  def word_count
    @normalized_phrase.split.inject(Hash.new(0)) { |c,w| c[w] +=1; c }
  end
end
