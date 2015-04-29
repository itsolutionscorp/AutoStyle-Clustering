class Hash
  def +(other)
    sum = ->(_, count1, count2) { count1 + count2 }

    merge(other, &sum)
  end
end


class Phrase
  def initialize content
    @content = content
  end

  def word_count
    sum = ->(_, count1, count2) { count1 + count2 }

    words.reduce({}) do |count, word|
      count + { word => 1 }
    end
  end

  def words
    @content.downcase.scan(/\w+/)
  end
end
