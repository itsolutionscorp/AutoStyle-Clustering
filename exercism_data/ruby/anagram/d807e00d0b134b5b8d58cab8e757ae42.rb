class Anagram < Struct.new(:word)
  def match(list)
    anagrams = []
    list.each do |potential|
      anagrams << potential if valid_anagram?(potential)
    end
    anagrams
  end

  private

  def valid_anagram?(other)
    word.downcase != other.downcase && word.downcase.chars.sort == other.downcase.chars.sort
  end
end
