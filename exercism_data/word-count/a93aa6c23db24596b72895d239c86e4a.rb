class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    words = sanitize(@string)
    words.each_with_object(Hash.new(0)) {|word, hash| hash[word] +=1 }
  end

  private

  def sanitize(string)
    string.downcase.scan(/\w*/).reject(&:empty?)
  end

end
