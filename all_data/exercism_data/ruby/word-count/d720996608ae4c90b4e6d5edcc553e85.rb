class Phrase

  def initialize(content)
    @content = content
  end

  def word_count
    WordArray.parse(@content).uniq_counts
  end
end

class WordArray < Array
  def self.parse(content)
    new(content.split(/[^\w]+/).map(&:downcase))
  end

  def uniq_counts
    counts = Hash.new(0)

    each do |word|
      counts[word] += 1
    end

    counts
  end
end
