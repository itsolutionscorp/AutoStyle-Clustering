class Words
  attr_reader :ordliste

  def initialize(phrase)
    @ordliste = phrase.gsub(/\W/, ' ').downcase.split(' ')
  end

  def count
    ordliste.uniq.reduce({}) do |count_summary, word|
      count_summary[word] = ordliste.count(word)
      count_summary
    end
  end
end
