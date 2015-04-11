class Phrase

  attr_reader :context
  def initialize context
    context = remove_punctuation(context)
    context = downcase_all_characters(context)
    @words  = split_the_context_by_space(context)
  end

  # convert the array of words into a hash
  def word_count
    data = Hash.new
    @words.each { |word|
      if data.has_key?(word)
        data[word] = data[word] + 1
      else
        data[word] = 1
      end
    }
    data
  end

  private

  def remove_punctuation context
    context = context.gsub(/[^0-9A-Za-z]/, " ")
  end

  def downcase_all_characters context
    context = context.downcase
  end

  def split_the_context_by_space(context)
    context = context.split(" ")
  end
end
