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
    counts = Hash.new
    @text.split(WORD_BOUNDS).each do |word|
      key = word.downcase
      if counts[key].nil?
        counts[key] = 0
      end
      counts[key] += 1
    end
    counts
  end

end
