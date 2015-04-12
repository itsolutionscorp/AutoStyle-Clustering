class Phrase

  def initialize(text)
    @text = text
  end

  def word_count
   if @counts.nil?
     @counts = count_words
   end
   @counts
  end

  private

  WORD_BOUNDS = /[^(\w|')]+/

  def count_words
    counts = Hash.new(0)
    @text.split(WORD_BOUNDS).each do |word|
      key = word.downcase
      counts[key] += 1
    end
    counts
  end

end
