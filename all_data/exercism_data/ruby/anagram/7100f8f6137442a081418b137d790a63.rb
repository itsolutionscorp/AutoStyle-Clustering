class Anagram
  attr_reader :source
  attr_reader :match_chars
  
  def initialize(source)
    @source   = source.downcase
    @match_chars = self.source.split('').sort
  end

  def match(candidates)
    candidates.reject {|word| duplicate?(word) }.select {|word| same_chars?(word) }
  end

  private
  def duplicate?(word)
    word.downcase.eql? source
  end

  def same_chars?(word)
    word.downcase.split('').sort.eql? match_chars
  end
end
