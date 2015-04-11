class Phrase

  def initialize(text)
    @text = text
    @count = Hash.new(0)
  end

  def split_to_words(test)
    @text.downcase
      .split(/[^a-zA-Z0-9\']/)
      .select{|x| not x.empty?}
  end

  def word_count
    if @count.empty?
      split_to_words(@text).each do |x| 
        @count[x] += 1 
      end
    end
    @count
  end
end
