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
      @count = split_to_words(@text).each do |x,y| 
        x[y] += 1 
        x 
      end
    end
    @count
  end
end
