class Phrase
  attr_accessor :phrase, :counts

  def initialize(phrase)
    @phrase = split(phrase)
    ignore_punctuation
  end

  def word_count
    @counts = {}
    @phrase.each do |word|
      if @counts.include? word
        @counts[word] += 1
      else
        @counts[word] = 1
      end
    end
    return @counts
  end

  private

  def split(phrase)
    @phrase.downcase.split(/[?!,\s]/)
  end

  def ignore_punctuation
    @phrase.delete_if { |word| word =~ /[?!:&@$%^]/ || word == '' }
  end

end
