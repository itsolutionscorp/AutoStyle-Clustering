class Words
  def initialize phrase
    @words = clean phrase
  end

  def count
    @words.each_with_object(Hash.new(0)) { |word, h|
      h[word] = h[word].to_i + 1
    }
  end

  private
  def clean phrase
    phrase.downcase.split(/\W+/)
  end
end
