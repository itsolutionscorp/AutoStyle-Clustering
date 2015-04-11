class Phrase

  attr_reader :word_count

  def initialize(input)
    @word_count = Hash.new(0)
    clean(input).split.each do |word|
      @word_count[word] += 1
    end
  end

  def clean(input)
    input.delete("!#@$%^&*()-+?/:").gsub(","," ").downcase
  end

end
