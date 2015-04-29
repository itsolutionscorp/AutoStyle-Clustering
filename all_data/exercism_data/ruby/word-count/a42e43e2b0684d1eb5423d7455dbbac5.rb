class Words
  attr_reader :list

  def initialize(phrase)
    @list = phrase.gsub(/\W/, ' ').downcase.split(' ')
  end

  def count
    list.uniq.reduce({}) do |count_summary, word|
      count_summary[word] = list.count(word)
      count_summary
    end
  end
end
