class Phrase

  def initialize(sentence)
    @sentence = sentence.downcase
  end

  def word_count
    analyse.each_with_object(Hash.new(0)) {|obj, hash| hash[obj] += 1 }
  end

  private
  def analyse
    @sentence.scan(/\w+/)
  end
end
