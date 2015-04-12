class Phrase
  
  def initialize(phrase)
    @phrase = phrase
  end
  
  def word_count
    tokens = @phrase.downcase.split(/\s|,/)
    tokens.inject(Hash.new(0)) do |word_list, token|
      token =~ /([A-Za-z0-9']+)/
      word_list[$1] = word_list[$1] + 1 if $1 && !$1.empty?
      word_list
    end
  end
  
end
