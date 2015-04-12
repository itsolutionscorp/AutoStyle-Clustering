class Phrase

  def initialize(the_phrase)
    @the_phrase = the_phrase
  end

  def word_count
    words =  @the_phrase.downcase.scan(/\w+/)
    words.each_with_object({}) { |x, wc| wc[x] = words.select { |b| b==x }.count }
  end

end
