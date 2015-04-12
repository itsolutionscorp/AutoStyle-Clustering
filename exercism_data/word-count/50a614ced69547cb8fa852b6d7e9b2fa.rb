class Words
  attr_reader :collection
  
  def initialize(sentence)
    @collection = prepare(sentence)
  end

  def count
    collection.each_with_object({}) do |word, h|
      h[word]=collection.count(word)
    end
  end
  
  private

  def prepare(sentence)
    sentence.gsub(/[^0-9A-Za-z]/, ' ').downcase.split(" ")
  end
end
