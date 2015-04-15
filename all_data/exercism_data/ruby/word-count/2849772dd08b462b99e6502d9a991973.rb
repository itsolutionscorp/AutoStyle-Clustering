class Phrase

  def initialize(phrase)
    @original = phrase
    words.each(&count)
  end

  def word_count
    @word_count ||= {}
  end

private

  def words
    @words ||=
      @original
        .downcase
        .split(/\W+/)
  end

  def count
    @counter_proc ||=
      proc do |word|
        word_count[word] ||= 0
        word_count[word] += 1
      end
  end

end
