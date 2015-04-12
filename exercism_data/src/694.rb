class Phrase

  def initialize text
    @text = text
  end

  def word_count
    return {} if @text.nil?
    @text.downcase.scan(/[\w']+/).each_with_object(Hash.new(0)) {|w,c| c[w] += 1 }
  end

end
