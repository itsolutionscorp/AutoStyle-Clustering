class Phrase
  attr_accessor :words, :phrase

  PUNCTUATION = %(!^"$%&\/\(\)=`*|<>@\:)
  PUNCTUATION_REG = Regexp.new("[#{PUNCTUATION}]")

  def initialize(phrase_string)
    self.phrase = phrase_string
    words = phrase_string.split(/[\s,;.!]/)
    self.words = words.
                  collect! {|w| w.gsub(PUNCTUATION_REG,"").downcase }.
                  reject {|w| w.empty?}
  end

  def word_count
    Hash[words.uniq.collect do |word|
      [word, words.select{|w| w == word}.count ]
    end]
  end
end
