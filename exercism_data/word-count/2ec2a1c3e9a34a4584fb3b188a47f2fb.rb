class Phrase
  attr_reader :words
  def initialize(input)
    @words = split_and_normalize(input)
  end

  def word_count
    word_map = Hash.new{|h,k| h[k] = 0}

    words.inject(word_map) do |map, word|
      map[word] += 1
      map
    end
  end

  def split_and_normalize(input)
    input.downcase.split(/\W/).
          reject {|word| word.empty?}
  end
end
