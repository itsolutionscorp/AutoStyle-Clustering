class Words
  def initialize phrase
    @words = clean phrase
  end

  def count
    zero = ->(h,k){ h[k] = 0 }
    @words.each_with_object(Hash.new(&zero)) { |word, h|
      h[word] = h[word].to_i + 1
    }
  end

  private
  def clean phrase
    phrase.downcase.split(/\W+/)
  end
end
