class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    @word_counts ||= build_word_counts
  end

  private
  attr_reader :text

  def build_word_counts
    counts = Hash.new { 0 }
    normalized_words.each do |word|
      counts[word] += 1
    end
    counts
  end

  def normalized_words
    words.map { |word| word.downcase }
  end

  def words
    text.scan(word_pattern)
  end

  def word_pattern
    /\w+/
  end
end

__END__
### VERSION 1 ###
class Phrase
  attr_reader :word_count
  def initialize(text)
    @word_count = text.scan(/\w+/).map { |word| word.downcase }.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end

### VERSION 2 ###
class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    @word_counts ||= build_word_counts
  end

  private
  def build_word_counts
    words = @text.scan(/\w+/)
    normalized_words = words.map { |word| word.downcase }
    normalized_words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end


### VERSION 3 ###
class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    @word_counts ||= build_word_counts
  end

  private
  attr_reader :text

  def build_word_counts
    counts = Hash.new { 0 }
    normalized_words.each do |word|
      counts[word] += 1
    end
    counts
  end

  def normalized_words
    words.map { |word| word.downcase }
  end

  def words
    text.scan(word_pattern)
  end

  def word_pattern
    /\w+/
  end
end

### VERSION 4 ###
class PhraseParser
  attr_reader :text
  def initialize(text)
    @text = text
  end

  def words
    text.scan(word_pattern)
  end

  private
  def word_pattern
    /\w+/
  end
end

class DowncasingWordNormalizer
  def normalize(word)
    word.downcase
  end
end

class WordCounter
  attr_reader :word_counts
  def initialize
    @word_counts = Hash.new(0)
  end

  def count(word)
    word_counts[word] += 1
  end
end

class Phrase
  def initialize(text)
    @phrase_parser = PhraseParser.new(text)
    @word_normalizer = DowncasingWordNormalizer.new
    @word_counter = WordCounter.new
  end

  def word_count
    @word_counts ||= build_word_counts
  end

  private
  attr_reader :phrase_parser
  attr_reader :word_normalizer
  attr_reader :word_counter

  def build_word_counts
    phrase_parser.words.each do |word|
      normalized_word = word_normalizer.normalize(word)
      word_counter.count(normalized_word)
    end

    word_counter.word_counts
  end
end
