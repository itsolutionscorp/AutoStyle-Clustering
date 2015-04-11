class Anagram
  def initialize(word)
    @insensitive = desensitize(word)
    @ordered = order(@insensitive)
  end

  def match(list)
    list.each_with_object([]) do |word, matches|
      insensitive = desensitize(word)
      next if insensitive == @insensitive
      ordered = order(insensitive)
      matches << word if ordered == @ordered
    end
  end

  private

  def desensitize(word)
    word.to_s.downcase
  end

  def order(insensitive)
    insensitive.chars.sort.join
  end
end
