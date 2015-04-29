class LetterCount
  def initialize(word)
    @word = word.downcase
  end

  def letters
    letter_count.keys.sort
  end

  def letter_count
    @letter_count ||= histogram(process)
  end

  private
  def histogram inp; hash = Hash.new(0); inp.each {|k,v| hash[k]+=1}; hash; end

  def process
    @word.split //
  end
end


class Anagram
  def initialize(word)
    @word = word.downcase
    @word_profile = LetterCount.new(@word).letter_count
  end

  def match(possibilities)
    possibilities.select{|possibility| match?(possibility)}
  end

  def match?(word) 
    (@word != word.downcase) && (@word_profile == LetterCount.new(word).letter_count)
  end

end
