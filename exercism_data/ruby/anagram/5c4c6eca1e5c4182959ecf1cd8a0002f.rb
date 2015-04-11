class Anagram
  def initialize(anagram)
    @original = anagram
  end

  def match(anagrams)
    anagrams.select do |anagram|
      self == Anagram.new(anagram)
    end
  end

  def ==(other)
    self.original != other.original &&
    self.signature == other.signature
  end

  protected

  def signature
    @signature ||= @original.downcase.chars.sort
  end

  def original
    @original.downcase
  end
end
