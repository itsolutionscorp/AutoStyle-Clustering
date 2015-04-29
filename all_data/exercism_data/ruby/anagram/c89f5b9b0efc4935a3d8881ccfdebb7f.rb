class Anagram
  def initialize(anagram)
    @original = anagram
  end

  def match(anagrams)
    anagrams.select do |anagram|
      different?(anagram) && same_content?(anagram)
    end
  end

  private

  def signature(item)
    @signature       ||= {}
    @signature[item] ||= item.downcase.chars.sort
  end

  def same_content?(item)
    signature(item) == signature(@original)
  end

  def different?(item)
    item.downcase != @original.downcase
  end
end
