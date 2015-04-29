class Phrase

  def initialize content
    @content = content
  end

  def word_count
    words.reduce({}) do |acc, w|
      acc.merge(w => acc.fetch(w, 0) + 1)
    end
  end

  private

  def words
    @content.downcase.scan(/[\w']+/)
  end
end
