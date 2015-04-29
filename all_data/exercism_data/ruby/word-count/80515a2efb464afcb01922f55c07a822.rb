class Phrase
  attr_accessor :words

  def initialize(words)
    self.words = split(words)
  end

  def word_count
    map = words
      .group_by { |w| w }
      .map      { |k,v| [k, v.size] }
    Hash[map]
  end

  private

  def split(words)
    words.gsub(",", " ")
         .gsub(/[^\w\s]/, "")
         .downcase
         .split(" ")
         .reject { |word| word == "" }
  end
end
