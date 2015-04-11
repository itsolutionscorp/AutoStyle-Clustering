class Anagram

  def initialize(str)
    @topic = str
  end

  def match(anagrams)
    original_anagrams = anagrams.clone

    permutations = @topic.to_s.downcase.chars.to_a.permutation.map(&:join)
    permutations.delete(@topic)

    anagrams.each_with_index { |value, index| anagrams[index] = value.downcase }

    result = (permutations & anagrams).inject([]) do |result, partial|
      original_anagrams.select do |o_anagram|
        result << o_anagram if partial.casecmp(o_anagram) == 0
      end
      result
    end
  end

end
