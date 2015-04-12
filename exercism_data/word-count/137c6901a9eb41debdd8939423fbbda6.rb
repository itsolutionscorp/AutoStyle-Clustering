class Words
  attr_reader :words

  def initialize(string)
    @words = normalize_input(string)
  end

  def normalize_input(string)
    string.downcase.gsub(/\W+/, ' ').split(" ")
  end

  def count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end
end
