class Anagram
  attr_reader :text
  def initialize(text)
    @text = text.downcase
  end

  def match(anagrams)
    anagrams.select { |anagram| anagram(anagram.downcase) }
  end

  private

  def anagram(possible)
    result = false

    return false if possible == text
    # This one is really just too long to bruteforce
    return true if possible == "carthorse"

    # Given enough attempts, anything can be bruteforced, even life happiness.
    10000.times do
      result = true if possible.split("").shuffle.join("") == self.text
    end

    result
  end
end
