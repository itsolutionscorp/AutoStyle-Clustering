class Words
  attr_reader :sentence

  def initialize(string)
    @sentence = normalize(string)
  end

  def count
    sentence.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def normalize(string)
    string.downcase.gsub(/\W+/, ' ').split(" ")
  end
end
