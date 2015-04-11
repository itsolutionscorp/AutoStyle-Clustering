class Anagram

  attr_reader :word

  def initialize word
    @word = word
  end

  def match list
    list.select{|candidate| anagram?(word.downcase, candidate.downcase)}
  end

  def anagram? target, candidate
    target != candidate && target.chars.sort.eql?(candidate.chars.sort)
  end
 
end
