class Phrase

  attr_reader :words

  def initialize(str)
    @words = parse str
  end

  def word_count
    occurrences = @words.inject({}) do |dict, word|
      increment_word_count dict, word

      dict
    end

    occurrences
  end

  private

  def parse(str)
    words_and_spaces = str.gsub /[^\w\s]/, ''

    words_and_spaces.downcase.split " "
  end

  def increment_word_count(dict, word)
    if dict[word]
      dict[word] += 1
    else
      dict[word] = 1
    end
  end

end

# My thoughts:
# 1. Constructor argument named `str` - best name I could come up with. Suggestions?
# 2. Calling `parse` in the constructor, since I want it to blow up as soon as 
#      possible if for some reason `parse` would throw.
# 3. I often hesitate to use inject/reduce/fold since it's not common knowledge
#      in my experience. What do you think about using this function in general?
