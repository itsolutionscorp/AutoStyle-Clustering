class Phrase

  def initialize(text)
    @input = text
  end

  def word_count
    tokenize(@input)
      .group_by{ |word| word }
      .each_with_object({}){ |(key, words), counts| counts[key] = words.count }
  end

  private

  def tokenize(input)
    input.downcase.split(/\W+/)
  end
end
