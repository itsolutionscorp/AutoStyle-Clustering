class Words
   def initialize source
    @words = source.downcase.split %r{\W}
  end

  def count
    word_count = Hash.new(0)

    @words.each do |word|
      word_count[word] = word_count[word] + 1
    end

    word_count.delete('')
    word_count
  end
end
