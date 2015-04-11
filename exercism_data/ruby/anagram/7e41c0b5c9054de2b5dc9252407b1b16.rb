class Anagram
  def initialize(word)
    @downcased, @ordered = analyse(word)
  end

  def match(list)
    list.each_with_object([]) do |word, matches|
      downcased, ordered = analyse(word)
      matches << word if ordered == @ordered && downcased != @downcased
    end
  end

  private

  def analyse(word)
    downcased = word.to_s.downcase
    ordered = downcased.chars.sort.join
    [downcased, ordered]
  end
end
