class Phrase
  def initialize content
    @content = content
  end

  def word_count
    sum = ->(_, count1, count2) { count1 + count2 }

    words.reduce({}) do |count, word|
      count.merge(word => 1, &sum)
    end
  end

  def words
    @content.downcase.split(/\W+/)
  end
end
