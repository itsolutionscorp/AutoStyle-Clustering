class Anagram

  def initialize(word)
    @word = NewString.new(word.downcase)
  end

  def match(list)    
    return list.keep_if{|s| @word.anagram?(s)}
  end

end

class NewString < String
  def initialize(s)
    super(s)
      @s_permutations = s.chars.to_a.permutation(s.length).to_a
  end
  def anagram?(s)
    return (s.casecmp(self) != 0 && @s_permutations.include?(s.downcase.chars.to_a))
  end
end
