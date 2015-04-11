class Anagram
  def initialize(original)
    @normalized = normalize(original)
  end

  def match(candidates)
    candidates.select do |candidate|
      anagram_of?(candidate)
    end
  end

  private

  def anagram_of?(other)
    @normalized == normalize(other)
  end

  def normalize(string)
    string.chars.sort
  end
end
