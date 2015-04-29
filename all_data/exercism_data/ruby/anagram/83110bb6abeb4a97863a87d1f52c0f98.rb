class Anagram
  attr_reader :detector
  def initialize(word)
    @detector = word
  end

  def match(words)
    words.select { |w| w.downcase.split('').sort == detector.downcase.split('').sort}
         .reject { |w| w.downcase == detector.downcase}
  end
end
