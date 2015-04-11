class Phrase
  def initialize(str)
    @text = str
  end

  def phrase_split(text)
    @words = text.gsub(/[^\w']/,'\1 \2')
                 .downcase
                 .split(' ')
  end

  def word_count
    phrase_split(@text).inject(Hash.new(0)) do |hsh, word|
      hsh[word] += 1
      hsh
    end
  end

end
