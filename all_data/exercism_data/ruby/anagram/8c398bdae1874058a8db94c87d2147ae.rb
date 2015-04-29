class Anagram

  def initialize(matcher)
    @matcher = matcher
  end

  def match(anagram_list)
    matches, result = [],[]
    @matcher.split('').permutation.to_a.each {|word| matches.push(word.join)}
    matches.each {|w| matches.delete(w) if w.casecmp(@matcher) == 0}
    anagram_list.each do |w|
      matches.each do |final|
        result.push(w) if w.casecmp(final) == 0
      end
    end
    result.uniq
  end

end
