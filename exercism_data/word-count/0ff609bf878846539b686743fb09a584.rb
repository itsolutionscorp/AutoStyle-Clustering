class Phrase
  def initialize(words)
  	@words = words.downcase.split(/\W+/)
  end

  def word_count
    @counts = @words.each_with_object({}) do |i, h|
      if h[i] == nil
        h[i] = 1
      else
        h[i] += 1
      end
    end
  end

end

#Oh boy is it ugly, but it works.  Please help!
