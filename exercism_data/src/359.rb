class Phrase
  attr_reader :text
  
  def initialize(text)
    @text = text
  end

  def word_count
    text.
    downcase.
    scan(/\w+/).
    each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end
