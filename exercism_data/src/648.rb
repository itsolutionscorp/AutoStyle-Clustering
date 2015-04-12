class Phrase
  attr_accessor :array_of_words

  def initialize(input)
    @array_of_words = input.gsub(",", ", ").split
  end

  def word_count
    @array_of_words.group_by do |word|
      word.match(/[[:word:]]*/).to_s.downcase
    end.each_with_object(Hash.new) do |tuple, hash|
      hash[tuple.first] = tuple.last.count unless tuple.first == ""
    end
  end
end
