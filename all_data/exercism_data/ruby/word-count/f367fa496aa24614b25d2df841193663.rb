class Words
  attr_reader :cleaned
  
  def initialize(input)
    @cleaned = input.gsub(/[^0-9A-Za-z]/, ' ').downcase
  end

  def processed
    cleaned.split(" ")
  end

  def count
    processed.each_with_object({}){|word, h|h[word]=processed.count(word)}
  end
end
