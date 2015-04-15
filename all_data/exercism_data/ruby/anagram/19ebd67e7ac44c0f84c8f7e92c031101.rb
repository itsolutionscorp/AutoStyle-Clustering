class Anagram

  def initialize( word )
    @word = word.downcase
    @mask = mask word
  end

  def match( words )
    words.select {|word| word.length == @mask.length && @word != word.downcase && mask( word ) == @mask }.uniq {|word| word.downcase }
  end

  private

  def mask( word )
    word.downcase.chars.sort
  end

end
