class Phrase

  def initialize(phrase)
    @phrase = phrase
    @counter = Counter.new
  end
  
  attr_reader :phrase

  def word_count
    if !@counter.counted_anything?
      calculate_word_count 
    end
    @counter.get_counts
  end

  private
  def calculate_word_count
    extract_words_from(phrase).each { |word| @counter.count_this(word) }
  end

  def extract_words_from(phrase)
    phrase.downcase.scan /\w+/
  end
end


class Counter
  def initialize
    @counts = Hash.new(0)
  end

  def counted_anything?
    !@counts.empty?
  end

  def count_this(thing)
    @counts[thing] += 1
  end

  def get_counts
    @counts
  end
end
