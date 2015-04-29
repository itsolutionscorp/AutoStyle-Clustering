class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  # Problems:
  # - Reg-exps are cryptic
  # - Not memoized
  def word_count
    results = {}
    @phrase.split(/ |,/).each do |word|
      word.downcase!
      word.tr!('^A-Za-z0-9', '')
      next if word.empty?
      results[word] ||= 0
      results[word] += 1
    end
    results
  end

end
