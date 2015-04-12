class Phrase
  def initialize s
    @s = s
  end

  def word_count
    words = Hash.new 0

    @s.scan(/[\w']+/) do |word|
      words[word.downcase] += 1
    end

    words
  end
end
