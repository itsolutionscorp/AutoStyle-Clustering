class Anagram 

  attr_reader :word

  def initialize(word)
    @word = word
  end

  def match(word_list)
    hash = create_hash(word)
    word_list.find_all{ |word| anagram?(hash, word) if not same? word }
  end

  private

  def anagram?(hash, anagram)
    hash == create_hash(anagram)
  end

  def same?(anagram)
    word.upcase == anagram.upcase
  end

  def create_hash(to_hash)
    to_hash.downcase.chars.sort
  end

end
