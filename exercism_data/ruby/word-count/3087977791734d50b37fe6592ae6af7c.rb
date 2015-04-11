class Phrase
  def initialize(phrase)
    @phrase = prepare_phrase(phrase)
  end

  def prepare_phrase(phrase)
    phrase.downcase!

    # split phrase string into an array
    if phrase.include? " "
      phrase = phrase.split(" ")
    else
      phrase = phrase.split(",")
    end

    # iterate over array to remove all but words and numbers
    phrase.each do |word|
      word.gsub!(/\p{^Alnum}/, "")
    end
    phrase.delete("")

    return phrase
  end

  def word_count
    @counted_words ||= count_words_once
  end

  private
    def count_words_once
      @phrase.each_with_object(Hash.new(0)) { |key, val| val[key] += 1 }
    end
end
