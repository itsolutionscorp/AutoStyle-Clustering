class Anagram
  def initialize(word)
    @sorted = sort_string(word)
  end

  def match(candidates)
    candidates.each_with_object([]) { |candidate, anagrams|
      if @sorted == sort_string(candidate)
        anagrams << candidate
      end
    }
  end

  private
  def sort_string(str)
    str.to_s.strip.downcase.chars.sort.join
  end
end
