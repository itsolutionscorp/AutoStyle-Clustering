class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase

    if @phrase.include? ' '
      @phrase = @phrase.split(" ")
    else
      @phrase = @phrase.split(",")
    end

    @group = {}
  end

  def word_count
    @counted_words ||= count_words_once
  end


  private

  def count_words_once
    remove_non_words
    @phrase.each do |word|
      if @group.include? word
        @group[word] += 1
      else
        @group[word] = 1
      end
    end
    @group
  end

  def remove_non_words
    @phrase.each do |word|
      word.gsub!(/\p{^Alnum}/, '')
    end
    @phrase.delete('')
  end

end
