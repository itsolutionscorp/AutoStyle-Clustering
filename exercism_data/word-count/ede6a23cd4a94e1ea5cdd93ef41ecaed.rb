class Phrase
  private
  EXCLUDE_CHARACTERS_REGEX=/[\s+<,!&@$%^:\.]/
  attr_accessor :sentence, :words

  def initialize(sentence)
    @words={}
    @sentence=sentence
  end

  def split_sentence
    sentence.split(EXCLUDE_CHARACTERS_REGEX).each do |word|
      next if word.empty?
      word.downcase!
      words[word]=words[word].to_i+1 #Slight trick since nil.to_1=0 and this make initializing an empty one cleaner.
    end
  end

  public
  def word_count
    split_sentence if words.empty?
    words
  end
end
