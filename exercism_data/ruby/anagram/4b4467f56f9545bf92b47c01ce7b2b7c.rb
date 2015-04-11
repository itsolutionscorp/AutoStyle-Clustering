class Anagram < Struct.new(:word)
  def match(list)
    anagrams = []
    list.each do |potential|
      anagrams << potential if valid_anagram?(potential)
    end
    anagrams
  end

  private

  def histogram
    histogram_for(word)
  end

  def histogram_for(other)
    other.each_char.each_with_object(Hash.new(0)) do |letter, hist|
      hist[letter.downcase] += 1
    end
  end

  def valid_anagram?(other)
    word.downcase != other.downcase && histogram == histogram_for(other)
  end
end
