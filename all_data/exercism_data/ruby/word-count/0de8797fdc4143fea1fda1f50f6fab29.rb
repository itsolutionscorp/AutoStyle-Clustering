class Phrase
  attr_writer :phrase

  def initialize(content)
    @content = content
  end

  def word_count
    word_list.inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end

private

  def word_list
    @content.downcase.split(/\W+/)
  end

end
