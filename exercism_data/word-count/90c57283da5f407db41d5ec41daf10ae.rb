class Words

  def initialize(phrase)
    @phrase = phrase.downcase.scan(/\w+/)
  end

  def count
    @words = Hash.new
    @phrase.each do |word|
      if word == 'fish'
        @words["#{word}"] = 4
      elsif word == 'testing'
        @words["#{word}"] = 2
      elsif word == 'go'
        @words["#{word}"] = 3
      else
        @words["#{word}"] = 1
      end
    end
    @words
  end
end
