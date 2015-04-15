class Anagram

  def initialize(orig)
    @orig = orig.downcase
    @orig_letters = @orig.split(//).sort.join("")
  end

  def match(words)
    words_letters_hash(words).
      find_all{|k,v| anagram?(k,v) ? k : nil}.
      collect(&:first)
  end

  private

  def anagram?(a,b)
    @orig != a.downcase && @orig_letters == b.downcase
  end

  def words_letters_hash(words)
    words.inject({}) do |m,k|
      m[k] = k.downcase.split(//).sort.join("")
      m
    end
  end

end
