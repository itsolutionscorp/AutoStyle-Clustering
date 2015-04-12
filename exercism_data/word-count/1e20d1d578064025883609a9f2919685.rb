class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.scan(/\w+/).map!{|k| k.downcase}
    words.inject(Hash.new(0)) {|h,k| h[k]+=1; h}
  end
end
