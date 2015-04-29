class Anagram
  def initialize(word)
    @word = word.downcase
  end

  def match(list)
    matches = []
    list.each do |candidate|
      matches << candidate if is_anagram(candidate)
    end
    matches
  end

  def is_anagram(candidate)
    candidate = candidate.downcase
    return false unless candidate.length == @word.length
    return false if candidate == @word 
    word_dup = @word.dup 
    word_ary = word_dup.split ""
    candidate.split("").each do |letter|
      rex = Regexp.compile letter
      pos = (word_dup =~ rex)
      return false unless pos 
      word_ary[pos] = ""
      word_dup = word_ary.join "" 
      word_ary = word_dup.split ""
    end 
    return true 
  end
end
