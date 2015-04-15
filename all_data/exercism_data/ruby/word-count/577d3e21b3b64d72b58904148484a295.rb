class Words
  attr_reader :words

  def initialize(string)
    @words = string.clean_for_counting_and_split
  end

  def count
    words.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end
end

class String
  def clean_for_counting_and_split
    downcase.gsub(/\W+/, ' ').split(" ")
  end
end
