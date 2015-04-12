class Words
  attr_reader :input
  
  def initialize(input)
    @input = input
  end

  def prepare
    input.gsub(/[^0-9A-Za-z]/, ' ').downcase.split(" ")
  end

  def count
    prepare.each_with_object({}){|word, h|h[word]=prepare.count(word)}
  end
end
