class Phrase
  attr_reader :list, :sentence

  def initialize(sentence)
    @sentence = sentence
    @list = prep_list
  end

  def word_count
    list.inject(Hash.new(0)) { |total, word| total[word] += 1 ; total }
  end

  def prep_list
    sentence.downcase.gsub(/[^a-z0-9\s]/, '').split
  end
end
