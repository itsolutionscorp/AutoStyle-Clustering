class Phrase

  attr_reader :list

  ONLY_WORDS_AND_NUMBERS = %r{\w+}

  def initialize(list)
    @list = list
  end

  def word_count
    counter  = Hash.new(0)
    filtered = list.downcase.scan(ONLY_WORDS_AND_NUMBERS)
    filtered.each_with_object(counter) do |word, counts|
      counts[word] += 1
    end
  end

end
