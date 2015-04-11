class Anagram

  def initialize(source)
    @source = source.downcase
    @source_characters = characters(@source)
  end

  def match(words)
    words.map(&:downcase).uniq.select { |match| same_characters?(match) && match != @source }
  end

  private

  def same_characters?(word)
    @source_characters == characters(word)
  end

  def characters(word)
    word.chars.sort.join
  end

end
